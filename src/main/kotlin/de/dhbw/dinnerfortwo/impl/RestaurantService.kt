package de.dhbw.dinnerfortwo.impl

import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

/**
 * The RestaurantService contains the operations related to managing Restaurants.
 */
@Service
class RestaurantService(
    private val restaurantRepository: RestaurantRepository,
    private val entityManagerFactory: EntityManagerFactory,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun getRestaurant(id: UUID): Restaurant {
        log.info("Looking for restaurant with id ${id}")
        return restaurantRepository.findByIdOrNull(id.toString())
            ?: throw EntityNotFoundException("Could not find restaurant with Id ${id}") // if null exception is thrown
    }

    @Transactional

    fun getAllRestaurants(): List<Restaurant> {
        log.info("Get all restaurants")
        return restaurantRepository.findAll().toList()
    }

    @Transactional
    fun create(restaurant: Restaurant): Restaurant {
        log.info("Save restaurant ${restaurant}")
        return restaurantRepository.save(restaurant)
    }

    @Transactional
    fun update(restaurant: Restaurant): Restaurant {
        val persisted = restaurantRepository.findByIdOrNull(restaurant.id) ?: throw EntityNotFoundException()
        val updated = persisted.copy(
            name = restaurant.name,
            email = restaurant.email,
            cuisine = restaurant.cuisine,
            rating = restaurant.rating
        )
        restaurantRepository.save(updated)
        log.info("Update restaurant ${updated}")
        return updated
    }

    fun createOrUpdate2(restaurant: Restaurant) {
        val entityManager = entityManagerFactory.createEntityManager()
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            log.info("Save or update restaurant ${restaurant}")

            entityManager.merge(restaurant)
        } catch (e: Exception) {
            transaction.rollback()
            log.error("Error during persist. Do rollback. Error was: ${e}", e)
            throw e
        } finally {
            if (transaction.isActive) {
                log.info("Committing transaction.")
                transaction.commit()
            }
        }
    }

    @Transactional

    fun delete(id: UUID) {
        log.info("Deleting restaurant with id ${id}")
        restaurantRepository.deleteById(id.toString())
    }
}
