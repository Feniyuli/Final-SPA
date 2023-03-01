package de.dhbw.dinnerfortwo.impl.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public PersonTO getPerson(long id) {
        log.info("Looking for an person with id {}", id);
        Person personById = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find person with Id " + id));

        PersonTO getPersonById = personById.toDTO();
        return getPersonById;
    }

    @Transactional
    public List<PersonTO> getAllPersons() {
        log.info("Get all persons");
        List<PersonTO> getAllPersons = ((List<Person>) personRepository.findAll())
                .stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());;


        return getAllPersons;
    }

    @Transactional
    public PersonTO create(PersonTO personTO) {
        log.info("Save or update person {}", personTO);

        Person person = Person.toEntity(personTO);
        Person savedEntity = personRepository.save(person);

        return savedEntity.toDTO();
    }
}
