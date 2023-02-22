package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonService;
import de.dhbw.dinnerfortwo.impl.person.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Person> getPerson(@PathVariable UUID id) {
        log.info("Get person with id {}", id);
        try {
            var person = personService.getPerson(id);
            return ResponseEntity.ok(person);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        log.info("Get all persons");
        var result = personService.getAllPersons();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO newPerson) {
        Person person = new Person(newPerson.getName(), newPerson.getAddress(), newPerson.getEmail(), newPerson.getType()); // enforce a new ID
        Person result = personService.create(person);
        log.info("Created person {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Update existing person, with a given ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePerson(@PathVariable UUID id, @RequestBody PersonDTO person) {
        Person updatePerson = new Person(id.toString(), person.getName(), person.getAddress(), person.getEmail(), person.getType()); // enforce the id of the parameter ID
        personService.update(updatePerson);
        log.info("updated person {}", updatePerson);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable UUID id) {
        personService.delete(id);
    }

    public static class PersonDTO {
        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String address;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private Type type;

        public PersonDTO() {
        }

        public PersonDTO(String name, String address, String email, Type type) {
            this.name = name;
            this.address = address;
            this.email = email;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", adress='" + address + '\'' +
                    ", email='" + email + '\'' +
                    ", Type='" + type + '\'' +
                    '}';
        }
    }
}
