package de.dhbw.dinnerfortwo.impl.rating;


import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The RatingService contains the operations related to managing Persons.
 */

@Service
public class RatingService {

    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    private final RatingRepository ratingRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public RatingTO getRate(long id){
        log.info("Looking for Rating with id{}", id);
        Rating ratingById = ratingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find rating with Id " + id));

        RatingTO getRate = ratingById.toDTO();

        return getRate;
    }

    @Transactional
    public List<RatingTO> getAllRatings(){
        log.info("Get all reservations");
        List<RatingTO> getAllRatings = ((List<Rating>) ratingRepository.findAll())
                .stream()
                .map(Rating::toDTO)
                .collect(Collectors.toList());
        ;

        return getAllRatings;
    }

    @Transactional
    public RatingTO create(RatingTO rating){
        log.info("Save or update rating {}", rating);

        Rating ratingTO = Rating.toEntity(rating);
        Rating savedEntity = ratingRepository.save(ratingTO);

        return savedEntity.toDTO();
    }

}
