package de.dhbw.dinnerfortwo.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

/**
 * The RestaurantService contains the operations related to managing Restaurants.
 */
@Service
class RestaurantService(private val restaurantRepository: RestaurantRepository) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getRestaurant(id: UUID): Restaurant {
        log.info("Looking for restaurant with id ${id}")
        return restaurantRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Could not find restaurant with Id ${id}") }
    }

    fun getAllRestaurants(): List<Restaurant> {
        log.info("Get all restaurants")
        return restaurantRepository.findAll().toList()
    }

    fun createOrUpdate(restaurant: Restaurant): Restaurant {
        log.info("Save or update restaurant ${restaurant}")
        return restaurantRepository.save(restaurant)
    }

    fun delete(id: UUID) {
        log.info("Deleting restaurant with id ${id}")
        restaurantRepository.deleteById(id)
    }
}
