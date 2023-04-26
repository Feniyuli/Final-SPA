package de.dhbw.dinnerfortwo.impl.item;

import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float price;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "restaurant", referencedColumnName = "id")
    private Restaurants restaurants;

    public Items(long id, String name, String description, float price, Restaurants restaurants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurants = restaurants;
    }

    public Items() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
        if (!(o instanceof Items)) return false;
        Items items = (Items) o;
        return getId() == items.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public ItemsTO toDTO(){

        ItemsTO itemsTO = new ItemsTO();
        BeanUtils.copyProperties( this, itemsTO);
        Restaurants restaurants = this.getRestaurants();
        RestaurantTO restaurantTO = restaurants.toDTO();

        itemsTO.setRestaurants(restaurantTO);
        return itemsTO;
    }

    public static Items toEntity(ItemsTO itemsTO){
        Items itemsToEntity = new Items();
        BeanUtils.copyProperties(itemsTO, itemsToEntity);
        RestaurantTO restaurantTO = itemsTO.getRestaurants();
        Restaurants restaurants = Restaurants.toEntity(restaurantTO);

        itemsToEntity.setRestaurants(restaurants);

        return itemsToEntity;
    }
}
