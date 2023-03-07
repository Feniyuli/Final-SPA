package de.dhbw.dinnerfortwo.impl.item;

import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;

public class ItemsTO {
    private long id;
    private String name;
    private String description;
    private float price;
    private RestaurantTO restaurants;

    public ItemsTO() {
    }

    public ItemsTO(long id, String name, String description, float price, RestaurantTO restaurants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurants = restaurants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RestaurantTO getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantTO restaurants) {
        this.restaurants = restaurants;
    }
}
