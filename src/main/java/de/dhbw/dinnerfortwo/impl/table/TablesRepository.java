package de.dhbw.dinnerfortwo.impl.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TablesRepository extends JpaRepository<Tables,Long> {
    @Query(value = """
      select t from Tables t\s
      where t.restaurants.id = :id\s
      """)
    List<Tables> findAllTablesByRestaurantId(Long id);
}
