package de.dhbw.dinnerfortwo.impl.person;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public List<PersonTO> getAllStaff() {
        log.info("Get all persons");

        List<PersonTO> staffs = new ArrayList<>();
        List<PersonTO> getAllPersons = ((List<Person>) personRepository.findAll())
                .stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());

        for(PersonTO personTO: getAllPersons){
            if(personTO.getType() == Type.Staff){
                if(personTO.getWorkplace() == 0){
                   staffs.add(personTO);
                }
            }
        }
        return staffs;
    }


    @Transactional
    public PersonTO create(PersonTO personTO) {
        log.info("Save or update person {}", personTO);

        Person person = Person.toEntity(personTO);
        Person savedEntity = personRepository.save(person);

        return savedEntity.toDTO();
    }

    public PersonTO getPersonByEmailAndPassword(String email, String password) {
        Person person = personRepository.getPersonByEmailAndPassword(email, password);
        return person.toDTO();
    }

    @Transactional
    public PersonTO addWorker(Long id, PersonTO personTO) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Person updatedPerson = person.get();

            updatedPerson.setWorkplace(personTO.getWorkplace());
            updatedPerson = personRepository.save(updatedPerson);
            return updatedPerson.toDTO();
        } else {
            throw new NotFoundException("could not find worker on id {" + id + "}.");
        }
    }

}
