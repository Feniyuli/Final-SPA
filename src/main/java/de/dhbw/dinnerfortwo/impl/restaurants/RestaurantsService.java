package de.dhbw.dinnerfortwo.impl.restaurants;

import de.dhbw.dinnerfortwo.impl.item.Items;
import de.dhbw.dinnerfortwo.impl.item.ItemsRepository;
import de.dhbw.dinnerfortwo.impl.table.Tables;
import de.dhbw.dinnerfortwo.impl.table.TablesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantsService {
    public RestaurantsService(RestaurantsRepository restaurantsRepository, TablesRepository tablesRepository, ItemsRepository itemsRepository) {
        this.restaurantsRepository = restaurantsRepository;
        this.tablesRepository = tablesRepository;
        this.itemsRepository = itemsRepository;
    }

    private final RestaurantsRepository restaurantsRepository;
    private final TablesRepository tablesRepository;
    private final ItemsRepository itemsRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public RestaurantTO getResto(long id) {
        log.info("Looking for a restaurant with id {}", id);
        Restaurants restaurantsById = restaurantsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find restaurants with Id " + id));

        RestaurantTO getRestaurantsById = restaurantsById.toDTO();

        return getRestaurantsById;
    }

    @Transactional
    public List<RestaurantTO> getAllResto() {
        log.info("Get all restaurants");
        List<RestaurantTO> getAllResto = ((List<Restaurants>) restaurantsRepository.findAll())
                .stream()
                .map(Restaurants::toDTO)
                .collect(Collectors.toList());;

        return getAllResto;
    }
    @Transactional
    public RestaurantTO create(RestaurantTO restaurants) {
        log.info("Save restaurant {}", restaurants);

        Restaurants restaurantsToEntity = Restaurants.toEntity(restaurants);
        Restaurants savedEntity = restaurantsRepository.save(restaurantsToEntity);

        return savedEntity.toDTO();
    }

    @Transactional
    public List<RestaurantTO> getAllRestaurantsByOwnerId(Long id){
        List<RestaurantTO> getAllResto= ((List<Restaurants>) restaurantsRepository.findAllRestaurantsByOwnerId(id))
                .stream()
                .map(Restaurants::toDTO)
                .collect(Collectors.toList());

        return getAllResto;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting rating with id {}", id);

        Restaurants restaurant = restaurantsRepository.findById(id).orElse(null);
        if (restaurant != null) {
            List<Tables> tables = tablesRepository.findAllTablesByRestaurantId(id);
            tablesRepository.deleteAll(tables);
            List<Items> items = itemsRepository.findAllItemsByRestaurantId(id);
            itemsRepository.deleteAll(items);
        } else {
            throw new IllegalArgumentException("Restaurant with ID " + id + " does not exist");
        }

        restaurantsRepository.deleteById(id);
    }

}
