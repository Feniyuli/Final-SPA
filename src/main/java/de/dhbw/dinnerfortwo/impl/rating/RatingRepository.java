package de.dhbw.dinnerfortwo.impl.rating;

import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository is responsible to interact with the database.
 * The default JpaRepository provides already all basic CRUD operations like reading or storing an entity.
 */

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = """
      SELECT t FROM Rating t\s
      WHERE t.restaurants.id = :id\s
      """)
    List<Rating> findAllRatinginRestaurant(Long id);

}



