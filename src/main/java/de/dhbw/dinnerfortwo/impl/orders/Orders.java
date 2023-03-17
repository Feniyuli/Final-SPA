package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;

import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Orders {
    @Id
    private long id;
    @Column(nullable = false)
    private boolean isPaid;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "person", referencedColumnName = "id")
    private Person person;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "restaurants", referencedColumnName = "id")
    private Restaurants restaurants;

    public Orders() {
    }

    public Orders(long id, boolean isPaid, Person person, Restaurants restaurants) {
        this.id = id;
        this.isPaid = isPaid;
        this.person = person;
        this.restaurants = restaurants;
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

    public Restaurants getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurants restaurants) {
        this.restaurants = restaurants;
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
        Person person = this.getPerson();
        PersonTO personTO = person.toDTO();
        Restaurants restaurants = this.getRestaurants();
        RestaurantTO restaurantTO = restaurants.toDTO();

        ordersTO.setPerson(personTO);
        ordersTO.setRestaurants(restaurantTO);
        return ordersTO;
    }

    public static Orders toEntity(OrdersTO ordersTO){
        Orders ordersToEntity = new Orders();
        BeanUtils.copyProperties(ordersTO, ordersToEntity);
        PersonTO personTO = ordersTO.getPerson();
        Person person = Person.toEntity(personTO);
        RestaurantTO restaurantTO = ordersTO.getRestaurants();
        Restaurants restaurants = Restaurants.toEntity(restaurantTO);

        ordersToEntity.setPerson(person);
        ordersToEntity.setRestaurants(restaurants);
        return ordersToEntity;
    }
}
