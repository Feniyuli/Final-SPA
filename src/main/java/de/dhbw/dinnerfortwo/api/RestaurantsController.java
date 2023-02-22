package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.RestaurantsController.URI_RESTAURANTS_BASE;

@RestController
@RequestMapping(value = URI_RESTAURANTS_BASE, produces = "application/json;charset=UTF-8")
public class RestaurantsController {
    public static final String URI_RESTAURANTS_BASE = URI_BASE + "/resto";

    private final RestaurantsService restaurantsService;


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RestaurantsController(RestaurantsService restaurantsService) {
        this.restaurantsService = restaurantsService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurants>> getAllResto() {
        log.info("Get all restaurants");
        var result = restaurantsService.getAllResto();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurants> createResto(@RequestBody RestaurantsController.RestaurantsDTO newRestaurants) {
        Restaurants restaurants = new Restaurants(newRestaurants.ownerId,
                newRestaurants.getName(), newRestaurants.getAddress(), newRestaurants.getDescription(),
                newRestaurants.getOpenTime(),newRestaurants.getCloseTime()); // enforce a new ID
        Restaurants result = restaurantsService.create(restaurants);
        log.info("Created restaurants {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    public static class RestaurantsDTO{
        @Column(nullable = false)
        private Person ownerId;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String address;

        @Column(nullable = false)
        private String description;

        @Column(nullable = false)
        private Timestamp openTime;

        @Column(nullable = false)
        private Timestamp closeTime;

        public RestaurantsDTO() {
        }

        public RestaurantsDTO(Person ownerId, String name, String address, String description, Timestamp openTime, Timestamp closeTime) {
            this.ownerId = ownerId;
            this.name = name;
            this.address = address;
            this.description = description;
            this.openTime = openTime;
            this.closeTime = closeTime;
        }

        public Person getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(Person ownerId) {
            this.ownerId = ownerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Timestamp getOpenTime() {
            return openTime;
        }

        public void setOpenTime(Timestamp openTime) {
            this.openTime = openTime;
        }

        public Timestamp getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(Timestamp closeTime) {
            this.closeTime = closeTime;
        }
    }


}
