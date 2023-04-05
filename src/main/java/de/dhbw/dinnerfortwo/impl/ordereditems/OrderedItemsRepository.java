package de.dhbw.dinnerfortwo.impl.ordereditems;

import de.dhbw.dinnerfortwo.impl.orders.Orders;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemsRepository extends JpaRepository<OrderedItems,Long> {
    @Query(value = "SELECT oi FROM OrderedItems oi WHERE oi.orders.id = :id")
    List<OrderedItems> getAllOrderedItemsByOrderId(Long id);
}
