package de.dhbw.dinnerfortwo.impl.reservation;

import de.dhbw.dinnerfortwo.impl.person.Person;
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
      select t from Reservation t\s
      where t.person.id = :id\s
      """)
    List<Reservation> findAllReservationByGuestId(Long id);


    @Query(value = """
      select t from Reservation t inner join Tables u \s
      on t.table.id = u.id\s
      where u.restaurants.id = :id\s
      """)
    List<Reservation> findAllReservationByRestaurantId(Long id);



}
