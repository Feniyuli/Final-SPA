package de.dhbw.dinnerfortwo.impl.person;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private int workplace;

    public Person() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWorkplace() {
        return workplace;
    }

    public void setWorkplace(int workplace) {
        this.workplace = workplace;
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