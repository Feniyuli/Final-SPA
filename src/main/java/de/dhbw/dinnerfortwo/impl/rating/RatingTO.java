package de.dhbw.dinnerfortwo.impl.rating;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;

public class RatingTO {
    private long id;
    private int rating;
    private PersonTO person;
    private RestaurantTO restaurants;

    public RatingTO(){
    }

    public RatingTO(long id, int rating, PersonTO person, RestaurantTO restaurants){
        this.id = id;
        this.rating = rating;
        this.person = person;
        this.restaurants = restaurants;
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

    public PersonTO getPerson() {
        return person;
    }

    public void setPerson(PersonTO person) {
        this.person = person;
    }

    public RestaurantTO getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantTO restaurants) {
        this.restaurants = restaurants;
    }






}
