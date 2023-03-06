package de.dhbw.dinnerfortwo.impl.ordereditems;

import de.dhbw.dinnerfortwo.impl.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemsRepository extends JpaRepository<Orders,Long> {
}
