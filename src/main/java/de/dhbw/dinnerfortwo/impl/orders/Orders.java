package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;

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

        OrdersTO ordersTO = new OrdersTO();
        BeanUtils.copyProperties( this, ordersTO);
        Person person = this.getPerson();
        PersonTO personTO = person.toDTO();

        ordersTO.setPerson(personTO);
        return ordersTO;
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
