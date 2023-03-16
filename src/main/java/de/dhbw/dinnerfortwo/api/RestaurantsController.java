package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.item.Items;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.*;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.RestaurantsController.URI_RESTAURANTS_BASE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_RESTAURANTS_BASE, produces = "application/json;charset=UTF-8")
public class RestaurantsController {
    public static final String URI_RESTAURANTS_BASE = URI_BASE + "/resto";
    @Autowired
    private final RestaurantsService restaurantsService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RestaurantsController(RestaurantsService restaurantsService) {
        this.restaurantsService = restaurantsService;
    }

    @GetMapping("/{restaurantId}/items")
    public List<Items> getItemsByRestaurantId(@PathVariable Long restaurantId) {
        return restaurantsService.getItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTO> getResto(@PathVariable long id) {
        log.info("Get Restaurants with id {}", id);
        try {
            var resto = restaurantsService.getResto(id);
            return ResponseEntity.ok(resto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTO>> getAllResto() {
        log.info("Get all restaurants");
        var result = restaurantsService.getAllResto();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantTO> createResto(@RequestBody RestaurantTO newRestaurants) {
        RestaurantTO result = restaurantsService.create(newRestaurants);
        log.info("Created restaurants {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
