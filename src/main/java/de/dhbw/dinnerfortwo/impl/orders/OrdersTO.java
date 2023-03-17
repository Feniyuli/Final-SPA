package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;

public class OrdersTO {
    private long id;
    private boolean isPaid;
    private PersonTO person;
    private RestaurantTO restaurants;

    public OrdersTO() {
    }

    public OrdersTO(long id, boolean isPaid, PersonTO person, RestaurantTO restaurants) {
        this.id = id;
        this.isPaid = isPaid;
        this.person = person;
        this.restaurants = restaurants;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public RestaurantTO getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantTO restaurants) {
        this.restaurants = restaurants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonTO getPerson() {
        return person;
    }

    public void setPerson(PersonTO person) {this.person = person; }

}
