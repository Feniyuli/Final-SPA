package de.dhbw.dinnerfortwo.api;


import de.dhbw.dinnerfortwo.impl.rating.RatingService;
import de.dhbw.dinnerfortwo.impl.rating.RatingTO;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationService;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.RatingController.URI_RATING_BASE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_RATING_BASE, produces = "application/json;charset=UTF-8")
public class RatingController {
    public static final String URI_RATING_BASE = URI_BASE + "/rating";

    private final RatingService ratingService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;

    }


    @GetMapping("/{id}")
    public ResponseEntity<List<RatingTO>> getAllRatings(@PathVariable long id) {
        log.info("Get rating with id {}", id);
        try {
            var rating = ratingService.getAllRatings();
            return ResponseEntity.ok(rating);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RatingTO>> getAllRatings() {
        log.info("Get all ratings");
        var result = ratingService.getAllRatings();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RatingTO> createRating(@RequestBody RatingTO newRating) {
        RatingTO result = ratingService.create(newRating);
        log.info("Created rating {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}
