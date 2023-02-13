package de.dhbw.dinnerfortwo.api

import de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE
import de.dhbw.dinnerfortwo.impl.Restaurant
import de.dhbw.dinnerfortwo.impl.RestaurantService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityNotFoundException

/**
 * REST (HTTP) API of the Dinner app to interact with the UI or external applications.
 * The REST API provides the CRUD operations to create, read, update or delete a restaurant.
 */
@RestController
@RequestMapping(RestaurantController.URI_RESTAURANT_BASE, produces = ["application/json;charset=UTF-8"])
class RestaurantController(private val restaurantService: RestaurantService) {
    companion object {
        // In Kotlin the static members have to be in the companion object
        const val URI_RESTAURANT_BASE = URI_BASE + "/restaurants"
    }

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{id}")
    fun getRestaurant(@PathVariable id: UUID): ResponseEntity<Restaurant> {
        log.info("Get restaurant with id: ${id}")
        try {
            val restaurant = restaurantService.getRestaurant(id)
            return ResponseEntity.ok(restaurant)
        } catch (e: EntityNotFoundException) {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllRestaurants(): ResponseEntity<List<Restaurant>> {
        log.info("Get all restaurants")
        val result = restaurantService.getAllRestaurants()
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping
    fun createRestaurant(@RequestBody newRestaurant: Restaurant): ResponseEntity<Restaurant> {
        val restaurant = newRestaurant.copy(id = UUID.randomUUID())
        val result = restaurantService.createOrUpdateRestaurant(restaurant)
        log.info("Created restaurant $result")
        return ResponseEntity(result, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateRestaurant(@PathVariable id: UUID, @RequestBody restaurant: Restaurant): ResponseEntity<Restaurant> {
        val updateRestaurant = restaurant.copy(id = id)
        val result = restaurantService.createOrUpdateRestaurant(updateRestaurant)
        log.info("Created restaurant $result")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteRestaurant(@PathVariable id: UUID) {
        restaurantService.deleteRestaurant(id)
    }
}
