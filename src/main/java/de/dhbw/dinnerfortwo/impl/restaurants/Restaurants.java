package de.dhbw.dinnerfortwo.impl.restaurants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.dinnerfortwo.impl.person.Person;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Restaurants {
    @Id
    //    @GeneratedValue
    private String id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "ownerid", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Timestamp openTime;

    @Column(nullable = false)
    private Timestamp closeTime;


    public Restaurants(Person ownerId, String name, String address, String description, Timestamp openTime, Timestamp closeTime) {
        this(UUID.randomUUID().toString(),ownerId, name, address, description, openTime, closeTime);
    }

    public Restaurants() {
    }

    public Restaurants(String id, Person ownerId, String name, String address, String description, Timestamp openTime, Timestamp closeTime) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Person ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    // equals and hash code must be based on the ID for JPA to work well.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurants restaurants = (Restaurants) o;

        return id.equals(restaurants.id);
    }

    // equals and hash code must be based on the ID for JPA to work well.
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Restaurants{" +
                "id=" + id +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", adress='" + address + '\'' +
                ", description='" + description + '\'' +
                ", openTime='" + description + '\'' +
                ", closeTime='" + description + '\'' +
                '}';
    }
}
