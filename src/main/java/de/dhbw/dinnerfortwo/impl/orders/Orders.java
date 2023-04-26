package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItems;
import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;

import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean isPaid;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "reservation", referencedColumnName = "id")
    private Reservation reservation;

    @OneToMany(targetEntity = OrderedItems.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "orderedItems", referencedColumnName = "id")
    private List<OrderedItems> orderedItems;

    @Column(nullable = false)
    private OrderStatus orderStatus;

    public Orders() {
    }

    public Orders(long id, boolean isPaid, Reservation reservation, List<OrderedItems> orderedItems, OrderStatus orderStatus) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public List<OrderedItems> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItems> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return getId() == orders.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public OrdersTO toDTO(){

        OrdersTO ordersTO = new OrdersTO();
        BeanUtils.copyProperties( this, ordersTO);
        ordersTO.setPaid(this.isPaid());
        Reservation reservation = this.getReservation();
        ReservationTO reservationTO = reservation.toDTO();

        List<OrderedItemsTO> orderedItemsTOS = new ArrayList<>();
        for (OrderedItems orderedItems : getOrderedItems()) {
            OrderedItemsTO orderedItemsTO = new OrderedItemsTO();
            orderedItemsTO = orderedItems.toDTO();
            orderedItemsTOS.add(orderedItemsTO);
        }

        ordersTO.setOrderedItems(orderedItemsTOS);
        ordersTO.setReservation(reservationTO);
        return ordersTO;
    }

    public static Orders toEntity(OrdersTO ordersTO){
        Orders ordersToEntity = new Orders();
        BeanUtils.copyProperties(ordersTO, ordersToEntity);
        ReservationTO reservationTO = ordersTO.getReservation();
        Reservation reservation = Reservation.toEntity(reservationTO);

        List<OrderedItems> orderedItems = new ArrayList<>();
        for (OrderedItemsTO orderedItemsTO : ordersTO.getOrderedItems()) {
            OrderedItems orderedItemstoEntity = OrderedItems.toEntity(orderedItemsTO);
            orderedItems.add(orderedItemstoEntity);
        }

        ordersToEntity.setOrderedItems(orderedItems);
        ordersToEntity.setReservation(reservation);
        return ordersToEntity;
    }
}
