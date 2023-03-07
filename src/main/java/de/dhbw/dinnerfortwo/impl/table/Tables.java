package de.dhbw.dinnerfortwo.impl.table;

//import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tables {
    @Id
    private long id;
    @Column(nullable = false)
    private int capacity;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "restaurant", referencedColumnName = "id")
    private Restaurants restaurants;

    public Tables(long id, int capacity, Restaurants restaurants) {
        this.id = id;
        this.capacity = capacity;
        this.restaurants = restaurants;
    }

    public Tables() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        if (!(o instanceof Tables)) return false;
        Tables tables = (Tables) o;
        return getId() == tables.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public de.dhbw.dinnerfortwo.impl.item.ItemsTO toDTO(){

        de.dhbw.dinnerfortwo.impl.item.ItemsTO itemsTO = new de.dhbw.dinnerfortwo.impl.item.ItemsTO();
        BeanUtils.copyProperties( this, itemsTO);
        Restaurants restaurants = this.getRestaurants();
        RestaurantTO restaurantTO = restaurants.toDTO();

        itemsTO.setRestaurants(restaurantTO);
        return itemsTO;
    }

    public static Tables toEntity(TablesTO itemsTO){
        Tables restaurantsToEntity = new Tables();
        BeanUtils.copyProperties(itemsTO, restaurantsToEntity);
        RestaurantTO restaurantTO = itemsTO.getRestaurants();
        Restaurants restaurants = Restaurants.toEntity(restaurantTO);

        restaurantsToEntity.setRestaurants(restaurants);

        return restaurantsToEntity;
    }
}
