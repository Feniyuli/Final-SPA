package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.person.PersonService;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.PersonController.URI_OWNER_BASE;

/**
 * REST (HTTP) API of the Dinner app to interact with the UI or external applications.
 * The REST API provides the CRUD operations to create, read, update or delete a restaurant.
 */
@RestController
@RequestMapping(value = URI_OWNER_BASE, produces = "application/json;charset=UTF-8")
public class PersonController {

    public static final String URI_OWNER_BASE = URI_BASE + "/persons";

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
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

    @PostMapping
    public ResponseEntity<PersonTO> createPerson(@RequestBody PersonTO newPerson) {
        PersonTO result = personService.create(newPerson);
        log.info("Created person {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
