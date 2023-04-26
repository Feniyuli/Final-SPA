package de.dhbw.dinnerfortwo.impl.rating;

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
    public RatingTO getRating(long id){
        log.info("Looking for Rating with id{}", id);
        Rating ratingById = ratingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find rating with Id " + id));

        RatingTO getRate = ratingById.toDTO();

        return getRate;
    }

    @Transactional
    public List<RatingTO> getAllRatings(){
        log.info("Get all Ratings");
        List<RatingTO> getAllRatings = ((List<Rating>) ratingRepository.findAll())
                .stream()
                .map(Rating::toDTO)
                .collect(Collectors.toList());

        return getAllRatings;
    }

    @Transactional
    public RatingTO create(RatingTO rating){
        log.info("Save rating {}", rating);

        Rating ratingTO = Rating.toEntity(rating);
        Rating savedEntity = ratingRepository.save(ratingTO);

        return savedEntity.toDTO();
    }

    @Transactional
    public List<RatingTO> getRestaurantRating(long id){
        List<RatingTO> getAllRatings = ((List<Rating>) ratingRepository.findAllRatinginRestaurant(id))
                .stream()
                .map(Rating::toDTO)
                .collect(Collectors.toList());

        return getAllRatings;
    }

    @Transactional
    public float getAverageRating(long id){
        List<RatingTO> getAllRatings = getRestaurantRating(id);
        int number = 0;
        float average = 0;

        for(RatingTO ratingTO: getAllRatings){
            average = average + ratingTO.getRating();
            number++;
        }

        return average / number;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting rating with id {}", id);
        ratingRepository.deleteById(id);
    }
}
