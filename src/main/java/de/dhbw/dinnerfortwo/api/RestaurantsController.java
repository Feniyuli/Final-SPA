package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.item.ItemsService;
import de.dhbw.dinnerfortwo.impl.item.ItemsTO;
import de.dhbw.dinnerfortwo.impl.orders.OrdersService;
import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationService;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantsService;
import de.dhbw.dinnerfortwo.impl.table.TablesService;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.RestaurantsController.URI_RESTAURANTS_BASE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_RESTAURANTS_BASE, produces = "application/json;charset=UTF-8")
public class RestaurantsController {
    public static final String URI_RESTAURANTS_BASE = URI_BASE + "/resto";

    private final RestaurantsService restaurantsService;
    private final ItemsService itemsService;
    private final ReservationService reservationService;
    private final OrdersService ordersService;
    private final TablesService tablesService;


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RestaurantsController(RestaurantsService restaurantsService, ItemsService itemsService, ReservationService reservationService, OrdersService ordersService, TablesService tablesService) {
        this.restaurantsService = restaurantsService;
        this.itemsService = itemsService;
        this.reservationService = reservationService;
        this.ordersService = ordersService;
        this.tablesService = tablesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTO> getResto(@PathVariable long id) {
        log.info("Get Restaurants with id {}", id);
        try {
            var resto = restaurantsService.getResto(id);
            return ResponseEntity.ok(resto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTO>> getAllResto() {
        log.info("Get all restaurants");
        var result = restaurantsService.getAllResto();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantTO> createResto(@RequestBody RestaurantTO newRestaurants) {
        RestaurantTO result = restaurantsService.create(newRestaurants);
        log.info("Created restaurants {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<ItemsTO>> getAllItemByRestaurantId(@PathVariable("id")Long id) {
        log.info("Get Items with Restaurant id {}");
        try {
            var items = itemsService.getAllItemByRestaurantId(id);
            return ResponseEntity.ok(items);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // RestaraurantId != GuestId!
    @GetMapping("/reservation/{id}")
    public ResponseEntity<List<ReservationTO>> getAllReservationByRestaurantId(@PathVariable("id")Long id) {
        log.info("Get reservation with id {}");
        try {
            var reservations = reservationService.getAllReservationByRestaurantId(id);
            return ResponseEntity.ok(reservations);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrdersTO>> getAllOrderByRestaurantId(@PathVariable("id")Long id) {
        log.info("Get all orders with restaurant id {}");
        try {
            var orders = ordersService.getAllOrdersByRestaurantId(id);
            return ResponseEntity.ok(orders);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<List<TablesTO>> getAllTablesByRestaurantId(@PathVariable("id")Long id) {
        log.info("Get tables with id {}");
        try {
            var tablesByRestaurantId = tablesService.getTablesByRestaurantId(id);
            return ResponseEntity.ok(tablesByRestaurantId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/revenue/{id}")
    public ResponseEntity<Float> getRevenue(@PathVariable long id){
        try {
            var resto = restaurantsService.getResto(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Not found");
        }
        var revenue = ordersService.getTotalRevenue(id);
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }

    @GetMapping("/availTables/{id}")
    public ResponseEntity<List<TablesTO>> getAvailableTables(@PathVariable("id")Long id, @RequestParam("localDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate){
        log.info("Get available tables with id {}");
        try {
            var tablesByRestaurantId = tablesService.getAvailableTables(id, localDate);
            return ResponseEntity.ok(tablesByRestaurantId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
