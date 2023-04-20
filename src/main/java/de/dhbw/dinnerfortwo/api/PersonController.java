package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.person.*;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationService;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantsService;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.PersonController.URI_OWNER_BASE;

/**
 * REST (HTTP) API of the Dinner app to interact with the UI or external applications.
 * The REST API provides the CRUD operations to create, read, update or delete a restaurant.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_OWNER_BASE, produces = "application/json;charset=UTF-8")
public class PersonController {

    public static final String URI_OWNER_BASE = URI_BASE + "/persons";

    private final PersonService personService;
    private final ReservationService reservationService;
    private final RestaurantsService restaurantsService;

    public PersonController(PersonService personService, ReservationService reservationService, RestaurantsService restaurantsService) {
        this.personService = personService;
        this.reservationService = reservationService;
        this.restaurantsService = restaurantsService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{id}")
    public ResponseEntity<PersonTO> getPerson(@PathVariable long id) {
        log.info("Get person with id {}", id);
        try {
            var person = personService.getPerson(id);
            return ResponseEntity.ok(person);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonTO>> getAllPersons() {
        log.info("Get all persons");
        var result = personService.getAllPersons();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<PersonTO>> getAllStaff() {
        log.info("Get all Staff");
        var result = personService.getAllStaff();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonTO> createPerson(@RequestBody PersonTO newPerson) {
        PersonTO result = personService.create(newPerson);
        log.info("Created person {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<List<ReservationTO>> getAllReservationByGuestId(@PathVariable("id")Long id) {
        log.info("Get reservation with id {}");
        try {
            var reservations = reservationService.getAllReservationByGuestId(id);
            return ResponseEntity.ok(reservations);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<List<RestaurantTO>> getAllRestaurantsByOwnerId(@PathVariable("id")Long id){
        log.info("Get restaurants with id {}");
        try {
            var restaurantsByOwnerId = restaurantsService.getAllRestaurantsByOwnerId(id);
            return ResponseEntity.ok(restaurantsByOwnerId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        PersonTO person = personService.getPersonByEmailAndPassword(email, password);

        if (person != null) {
            session.setAttribute("user", person);
            return ResponseEntity.ok(new LoginResponse(true, person));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, null));
        }
    }

    @PutMapping("/addWorker/{id}")
    public ResponseEntity<PersonTO> addWorker(@PathVariable Long id, @RequestBody PersonTO personTO) {
        PersonTO result = personService.addWorker(id, personTO);
        log.info("updated person {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
