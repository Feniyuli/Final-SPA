package de.dhbw.dinnerfortwo.impl.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The repository is responsible to interact with the database.
 * The default JpaRepository provides already all basic CRUD operations like reading or storing an entity.
 */
@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long> {
    @Query(value = """
      select t from Restaurants t\s
      where t.owner.id = :id\s
      """)
    List<Restaurants> findAllRestaurantsByOwnerId(Long id);
}
