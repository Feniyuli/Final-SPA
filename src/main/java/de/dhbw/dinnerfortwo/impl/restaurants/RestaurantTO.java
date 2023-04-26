package de.dhbw.dinnerfortwo.impl.restaurants;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RestaurantTO {
    private long id;
    private PersonTO owner;
    private String name;
    private String address;
    private String description;
    private String openTime;
    private String closeTime;
    private String picture;

    public RestaurantTO() {
    }

    public RestaurantTO(int id, PersonTO owner, String name, String address, String description, LocalTime openTime, LocalTime closeTime, String picture) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.description = description;
        this.openTime = openTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.closeTime = closeTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        this.picture = picture;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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
}
