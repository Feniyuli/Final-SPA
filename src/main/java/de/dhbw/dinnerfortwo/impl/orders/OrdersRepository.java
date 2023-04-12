package de.dhbw.dinnerfortwo.impl.orders;

import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    @Query(value = """
      SELECT t FROM Orders t INNER JOIN Reservation u \s
      ON t.reservation.id = u.id\s
      INNER JOIN Tables b \s 
      ON u.table.id = b.id\s
      WHERE b.restaurants.id = :id\s
      """)
    List<Orders> findAllOrders(Long id);

    @Query(value = """
      SELECT t FROM Orders t\s
      WHERE t.reservation.id = :id\s
      """)
    List<Orders> findOrderReserved(Long id);
}
