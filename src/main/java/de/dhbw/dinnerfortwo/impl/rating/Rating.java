package de.dhbw.dinnerfortwo.impl.rating;

import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.beans.BeanUtils;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name= "restaurants", referencedColumnName = "id")
    private Restaurants restaurants;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guest", referencedColumnName = "id")
    private Person person;

    public Rating(){
    }

    public Rating(long id, int rating, String comment, Restaurants restaurants, Person person) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.restaurants = restaurants;
        this.person = person;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Restaurants getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurants restaurants) {
        this.restaurants = restaurants;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public  boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating = (Rating) o;
        return getId() == rating.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    public RatingTO toDTO(){

        RatingTO ratingTO = new RatingTO();
        BeanUtils.copyProperties( this, ratingTO);

        Restaurants restaurants = this.getRestaurants();
        RestaurantTO restaurantTO = restaurants.toDTO();

        Person person = this.getPerson();
        PersonTO personTO = person.toDTO();

        ratingTO.setRestaurants(restaurantTO);
        ratingTO.setPerson(personTO);
        return ratingTO;
    }

    public static Rating toEntity(RatingTO ratingTO){
        Rating ratingToEntity = new Rating();
        BeanUtils.copyProperties(ratingTO, ratingToEntity);

        RestaurantTO restaurantTO = ratingTO.getRestaurants();
        Restaurants restaurants = Restaurants.toEntity(restaurantTO);

        PersonTO personTO = ratingTO.getPerson();
        Person person = Person.toEntity(personTO);

        ratingToEntity.setRestaurants(restaurants);
        ratingToEntity.setPerson(person);
        return ratingToEntity;
    }

}


