package de.dhbw.dinnerfortwo.impl.item;

import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Long> {
    List<Items> findByRestaurants(Restaurants restaurant);
}
