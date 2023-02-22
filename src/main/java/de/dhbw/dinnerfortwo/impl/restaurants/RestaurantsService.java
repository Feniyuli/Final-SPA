package de.dhbw.dinnerfortwo.impl.restaurants;

import de.dhbw.dinnerfortwo.impl.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * The PersonService contains the operations related to managing Persons.
 */
@Service
public class RestaurantsService {
    public RestaurantsService(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    private final RestaurantsRepository restaurantsRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Restaurants getResto(UUID id) {
        log.info("Looking for a restaurant with id {}", id);
        return restaurantsRepository.findById(id.toString()).orElseThrow(() -> new EntityNotFoundException("Could not find restaurants with Id " + id));
    }

    @Transactional
    public List<Restaurants> getAllResto() {
        log.info("Get all restaurants");
        return restaurantsRepository.findAll().stream().toList();
    }

    @Transactional
    public Restaurants create(Restaurants restaurants) {
        log.info("Save or update restaurant {}", restaurants);
        return restaurantsRepository.save(restaurants);
    }
}
