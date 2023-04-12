package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.orders.OrdersService;
import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
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
import static de.dhbw.dinnerfortwo.api.ReservationController.URI_RESERVATION_BASE;

//base can't be angepasst?

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_RESERVATION_BASE, produces = "application/json;charset=UTF-8")
public class ReservationController {
    public static final String URI_RESERVATION_BASE = URI_BASE + "/reservation";

    private final ReservationService reservationService;
    private final OrdersService ordersService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ReservationController(ReservationService reservationService, OrdersService ordersService) {
        this.reservationService = reservationService;
        this.ordersService = ordersService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservationTO> getAllRes(@PathVariable long id) {
        log.info("Get reservation with id {}", id);
        try {
            var reservations = reservationService.getRes(id);
            return ResponseEntity.ok(reservations);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservationTO>> getAllRes() {
        log.info("Get all reservations");
        var result = reservationService.getAllRes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationTO> createReservation(@RequestBody ReservationTO newReservation) {
        ReservationTO result = reservationService.create(newReservation);
        log.info("Created reservation {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrdersTO>> getOrderReserved(@PathVariable long id) {
        log.info("Get order with id {}", id);
        try {
            var orders = ordersService.getOrderReserved(id);
            return ResponseEntity.ok(orders);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
