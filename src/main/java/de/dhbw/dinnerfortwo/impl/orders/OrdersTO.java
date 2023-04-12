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
    private PersonTO person;
    private ReservationTO reservation;
    private List<OrderedItemsTO> orderedItems;

    public OrdersTO() {
    }

    public OrdersTO(long id, boolean isPaid, PersonTO person, ReservationTO reservation, List<OrderedItemsTO> orderedItems) {
        this.id = id;
        this.isPaid = isPaid;
        this.person = person;
        this.reservation = reservation;
        this.orderedItems = orderedItems;
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

    public PersonTO getPerson() {
        return person;
    }

    public void setPerson(PersonTO person) {this.person = person; }

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
