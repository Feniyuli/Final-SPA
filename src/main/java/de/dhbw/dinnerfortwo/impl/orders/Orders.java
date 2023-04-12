package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItems;
import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;

import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
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
    @JoinColumn(name = "person", referencedColumnName = "id")
    private Person person;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "reservation", referencedColumnName = "id")
    private Reservation reservation;
    @OneToMany(targetEntity = OrderedItems.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "orderedItems", referencedColumnName = "id")
    private List<OrderedItems> orderedItems;

    public Orders() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        Person person = this.getPerson();
        PersonTO personTO = person.toDTO();
        Reservation reservation = this.getReservation();
        ReservationTO reservationTO = reservation.toDTO();

        List<OrderedItemsTO> orderedItemsTOS = new ArrayList<>();
        for (OrderedItems orderedItems : getOrderedItems()) {
            OrderedItemsTO orderedItemsTO = new OrderedItemsTO();
            orderedItemsTO = orderedItems.toDTO();
            orderedItemsTOS.add(orderedItemsTO);
        }

        ordersTO.setOrderedItems(orderedItemsTOS);
        ordersTO.setPerson(personTO);
        ordersTO.setReservation(reservationTO);
        return ordersTO;
    }

    public static Orders toEntity(OrdersTO ordersTO){
        Orders ordersToEntity = new Orders();
        BeanUtils.copyProperties(ordersTO, ordersToEntity);
        PersonTO personTO = ordersTO.getPerson();
        Person person = Person.toEntity(personTO);
        ReservationTO reservationTO = ordersTO.getReservation();
        Reservation reservation = Reservation.toEntity(reservationTO);

        List<OrderedItems> orderedItems = new ArrayList<>();
        for (OrderedItemsTO orderedItemsTO : ordersTO.getOrderedItems()) {
            OrderedItems orderedItemstoEntity = OrderedItems.toEntity(orderedItemsTO);
            orderedItems.add(orderedItemstoEntity);
        }

        ordersToEntity.setOrderedItems(orderedItems);
        ordersToEntity.setPerson(person);
        ordersToEntity.setReservation(reservation);
        return ordersToEntity;
    }
}
