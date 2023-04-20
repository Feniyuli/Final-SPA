package de.dhbw.dinnerfortwo.impl.restaurants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Timestamp openTime;

    @Column(nullable = false)
    private Timestamp closeTime;

    @Column(nullable = false)
    private String picture;


    public Restaurants() {
    }

    public Restaurants(long id, Person owner, String name, String address, String description, Timestamp openTime, Timestamp closeTime, String picture) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person ownerId) {
        this.owner = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    // equals and hash code must be based on the ID for JPA to work well.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurants)) return false;
        Restaurants that = (Restaurants) o;
        return getId() == that.getId() && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getName(), that.getName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getOpenTime(), that.getOpenTime()) && Objects.equals(getCloseTime(), that.getCloseTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public RestaurantTO toDTO(){

        RestaurantTO restaurantTO = new RestaurantTO();
        BeanUtils.copyProperties( this, restaurantTO);
        Person person = this.getOwner();
        PersonTO personTO = person.toDTO();

        restaurantTO.setOwner(personTO);
        return restaurantTO;
    }

    public static Restaurants toEntity(RestaurantTO restaurantTO){
        Restaurants restaurantsToEntity = new Restaurants();
        BeanUtils.copyProperties(restaurantTO, restaurantsToEntity);
        PersonTO personTO = restaurantTO.getOwner();
        Person person = Person.toEntity(personTO);

        restaurantsToEntity.setOwner(person);

        return restaurantsToEntity;
    }
}
