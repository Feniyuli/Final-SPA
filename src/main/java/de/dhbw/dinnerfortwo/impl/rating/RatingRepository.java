package de.dhbw.dinnerfortwo.impl.rating;

import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * The repository is responsible to interact with the database.
 * The default JpaRepository provides already all basic CRUD operations like reading or storing an entity.
 */

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}



