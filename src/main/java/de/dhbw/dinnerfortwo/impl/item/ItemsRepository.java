package de.dhbw.dinnerfortwo.impl.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Long> {
    @Query(value = """
      select t from Items t\s
      where t.restaurants.id = :id\s
      """)
    List<Items> findAllItemsByRestaurantId(Long id);
}
