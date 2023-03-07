package de.dhbw.dinnerfortwo.impl.table;

import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesService {

    private final de.dhbw.dinnerfortwo.impl.table.TablesRepository tablesRepository;
    public TablesService(de.dhbw.dinnerfortwo.impl.table.TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //@Transactional
    //public List<TablesTO> getAllTables() {
    //    log.info("Get all tables");
    //    List<TablesTO> getAllTables = ((List<Tables>) tablesRepository.findAll())
    //            .stream()
    //            .map(Tables::toDTO)
    //            .collect(Collectors.toList());;

    //    return getAllTables;
    //}
    //@Transactional
    //public de.dhbw.dinnerfortwo.impl.table.TablesTO create(de.dhbw.dinnerfortwo.impl.table.TablesTO tablesTO) {
    //    log.info("Save or update Tables {}", tablesTO);

    //    Tables tablesToEntity = Tables.toEntity(tabelsTO);
    //    Tables savedEntity = tablesRepository.save(tablesToEntity);

    //    return savedEntity.toDTO();
    //}
}
