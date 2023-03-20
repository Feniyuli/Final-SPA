package de.dhbw.dinnerfortwo.impl.restaurants;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import java.sql.Timestamp;

public class RestaurantTO {
    private long id;
    private PersonTO owner;
    private String name;
    private String address;
    private String description;
    private Timestamp openTime;
    private Timestamp closeTime;
    private String picture;

    public RestaurantTO() {
    }

    public RestaurantTO(long id, PersonTO owner, String name, String address, String description, Timestamp openTime, Timestamp closeTime, String picture) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonTO getOwner() {
        return owner;
    }

    public void setOwner(PersonTO owner) {
        this.owner = owner;
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
}
