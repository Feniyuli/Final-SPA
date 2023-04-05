package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItems;
import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;

import java.util.List;

public class OrdersTO {
    private long id;
    private boolean isPaid;
    private PersonTO person;
    private RestaurantTO restaurants;
    private List<OrderedItemsTO> orderedItems;

    public OrdersTO() {
    }

    public OrdersTO(long id, boolean isPaid, PersonTO person, RestaurantTO restaurants, OrderedItemsTO orderedItems) {
        this.id = id;
        this.isPaid = isPaid;
        this.person = person;
        this.restaurants = restaurants;
        this.orderedItems = (List<OrderedItemsTO>) orderedItems;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean paid) {
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

    public OrderedItemsTO getOrderedItems() {
        return (OrderedItemsTO) orderedItems;
    }

    public void setOrderedItems(OrderedItemsTO orderedItems) {
        this.orderedItems = (List<OrderedItemsTO>) orderedItems;
    }

}
