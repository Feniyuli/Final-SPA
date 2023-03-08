package de.dhbw.dinnerfortwo.impl.reservation;

import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
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
public class ReservationService {
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    private final ReservationRepository reservationRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Transactional
    public ReservationTO getRes(long id){
        log.info("Looking for a reservation  with id {}", id);
        Reservation reservationById = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find reservation with Id " + id));

        ReservationTO getResById = reservationById.toDTO();

        return getResById;
    }


    @Transactional
    public List<ReservationTO> getAllRes() {
        log.info("Get all reservations");
        List<ReservationTO> getAllRes = ((List<Reservation>) reservationRepository.findAll())
                .stream()
                .map(Reservation::toDTO)
                .collect(Collectors.toList());
        ;

        return getAllRes;
    }

    @Transactional
    public ReservationTO create(ReservationTO reservation) {
        log.info("Save or update reservation {}", reservation);

        Reservation reservationTO = Reservation.toEntity(reservation);
        Reservation savedEntity = reservationRepository.save(reservationTO);

        return savedEntity.toDTO();
    }

}

//why getRes grey?