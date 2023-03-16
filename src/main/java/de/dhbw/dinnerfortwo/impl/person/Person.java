package de.dhbw.dinnerfortwo.impl.person;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Person {
    @Id
    //    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Type type;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"owner", "hibernateLazyInitializer", "handler"})
    private Set<Restaurants> restaurants;

    public Person() {
    }

    public Person(long id, String name, String address, String email, Type type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    // equals and hash code must be based on the ID for JPA to work well.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getName(), person.getName()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getEmail(), person.getEmail()) && getType() == person.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public PersonTO toDTO(){

        PersonTO personTO = new PersonTO();
        BeanUtils.copyProperties( this, personTO);
        return personTO;
    }

    public static Person toEntity(PersonTO personTO){
        Person personToEntity = new Person();
        BeanUtils.copyProperties( personTO, personToEntity);

        return personToEntity;
    }
}