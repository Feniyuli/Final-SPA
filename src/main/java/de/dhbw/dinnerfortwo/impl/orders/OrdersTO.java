package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;

public class OrdersTO {
    private long id;
    private boolean isPaid;
    private PersonTO person;

    public OrdersTO() {
    }

    public OrdersTO(long id, boolean isPaid, PersonTO person) {
        this.id = id;
        this.isPaid = isPaid;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public PersonTO getPerson() {
        return person;
    }

    public void setPerson(PersonTO person) {this.person = person; }

}
