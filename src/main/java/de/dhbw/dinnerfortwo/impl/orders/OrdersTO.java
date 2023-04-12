package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;

import java.util.List;

public class OrdersTO {
    private long id;
    private boolean isPaid;
    private ReservationTO reservation;
    private List<OrderedItemsTO> orderedItems;

    public OrdersTO() {
    }


    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderedItemsTO> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItemsTO> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public ReservationTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationTO reservation) {
        this.reservation = reservation;
    }
}
