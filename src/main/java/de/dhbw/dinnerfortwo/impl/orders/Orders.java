package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
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

    public Orders(long id, boolean isPaid, Person person) {
        this.id = id;
        this.isPaid = isPaid;
        this.person = person;
    }

    public Orders() {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

        OrdersTO orderedItemsTO = new OrdersTO();
        BeanUtils.copyProperties( this, orderedItemsTO);
        Person person = this.getPerson();
        PersonTO personTO = person.toDTO();

        OrdersTO.setPerson(personTO);
        return OrdersTO;
    }

    public static Orders toEntity(OrdersTO ordersTO){
        Orders ordersToEntity = new Orders();
        BeanUtils.copyProperties(ordersTO, ordersToEntity);
        PersonTO personTO = ordersTO.getPerson();
        Person person = Person.toEntity(personTO);

        ordersToEntity.setPerson(person);

        return ordersToEntity;
    }
}
