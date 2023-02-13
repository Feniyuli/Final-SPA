package de.dhbw.dinnerfortwo.impl

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * The repository is responsible to interact with the database.
 * The default JpaRepository provides already all basic CRUD operations like reading or storing an entity.
 */
@Repository
interface RestaurantRepository : JpaRepository<Restaurant, UUID>
