package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;

import java.util.List;

public class OrdersTO {
    private long id;
    private boolean isPaid;
    private ReservationTO reservation;
    private List<OrderedItemsTO> orderedItems;
    private OrderStatus orderStatus;

    public OrdersTO() {
    }

    public OrdersTO(long id, boolean isPaid, ReservationTO reservation, List<OrderedItemsTO> orderedItems, OrderStatus orderStatus) {
        this.id = id;
        this.isPaid = isPaid;
        this.reservation = reservation;
        this.orderedItems = orderedItems;
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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
