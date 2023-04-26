package de.dhbw.dinnerfortwo.impl.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The repository is responsible to interact with the database.
 * The default JpaRepository provides already all basic CRUD operations like reading or storing an entity.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
      SELECT t FROM Reservation t\s
      WHERE t.person.id = :id\s
      """)
    List<Reservation> findAllReservationByGuestId(Long id);


    @Query(value = """
      SELECT t FROM Reservation t INNER JOIN Tables u \s
      ON t.table.id = u.id\s
      WHERE u.restaurants.id = :id\s
      """)
    List<Reservation> findAllReservationByRestaurantId(Long id);
}
