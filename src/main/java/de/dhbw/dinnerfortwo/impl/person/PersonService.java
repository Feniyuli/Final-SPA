package de.dhbw.dinnerfortwo.impl.person;

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
public class PersonService {
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final PersonRepository personRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Person getPerson(UUID id) {
        log.info("Looking for an person with id {}", id);
        return personRepository.findById(id.toString()).orElseThrow(() -> new EntityNotFoundException("Could not find person with Id " + id));
    }

    @Transactional
    public List<Person> getAllPersons() {
        log.info("Get all persons");
        return personRepository.findAll().stream().toList();
    }

    @Transactional
    public Person create(Person person) {
        log.info("Save or update person {}", person);
        return personRepository.save(person);
    }

    @Transactional
    public void update(Person person) {
        var persisted = personRepository.findById(person.getId()).orElseThrow(() -> new EntityNotFoundException("Could not find person with Id " + person.getId()));
        persisted.setName(person.getName());
        persisted.setAddress(person.getAddress());
        persisted.setEmail(person.getEmail());
        personRepository.save(person);
    }

    public void delete(UUID id) {
        log.info("Deleting person with id {}", id);
        personRepository.deleteById(id.toString());
    }
}
