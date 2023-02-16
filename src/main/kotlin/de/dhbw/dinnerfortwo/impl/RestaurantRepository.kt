package de.dhbw.dinnerfortwo.impl

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * The repository is responsible to interact with the database.
 * The default JpaRepository provides already all basic CRUD operations like reading or storing an entity.
 */
@Repository
interface RestaurantRepository : JpaRepository<Restaurant, String> {
//    @Modifying
//    @Query("update Restaurant r set r.name = :#{#restaurant.name}, r.cuisine = :#{#restaurant.cuisine}, r.email = :#{#restaurant.email}, r.rating = :#{#restaurant.rating} where r.id = :#{#restaurant.id}")
//    fun updateRestaurant(restaurant: Restaurant)
}
