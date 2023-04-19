package de.dhbw.dinnerfortwo.impl.table;

import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;

public class TablesTO {
    private long id;
    private int capacity;
    private int tableNumber;
    private RestaurantTO restaurants;

    public TablesTO() {
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RestaurantTO getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantTO restaurants) {
        this.restaurants = restaurants;
    }
}
