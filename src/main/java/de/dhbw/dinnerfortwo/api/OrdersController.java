package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.orders.OrdersService;
import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.OrdersController.URI_ORDERS_BASE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_ORDERS_BASE, produces = "application/json;charset=UTF-8")
public class OrdersController {
    public static final String URI_ORDERS_BASE = URI_BASE + "/orders";

    private final OrdersService ordersService;


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersTO> getOrder(@PathVariable long id) {
        log.info("Get Orders with id {}", id);
        try {
            var order = ordersService.getOrder(id);
            return ResponseEntity.ok(order);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrdersTO>> getAllOrders() {
        log.info("Get all orders");
        var result = ordersService.getAllOrders();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdersTO> createOrder(@RequestBody OrdersTO newOrders) {
        OrdersTO result = ordersService.create(newOrders);
        log.info("Created orders {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<Float> getTotalItem(@PathVariable long id){
        try {
            var order = ordersService.getOrder(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Something went wrong.");
        }
        var amount = ordersService.getTotalOrder(id);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

    @PutMapping("payOrder/{id}")
    public ResponseEntity<OrdersTO> updateOrderIsPaid(@PathVariable Long id) {
        OrdersTO updatedOrder = ordersService.updateOrderIsPaid(id);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("onProcess/{id}")
    public ResponseEntity<OrdersTO> onProcess(@PathVariable Long id) {
        OrdersTO updatedOrder = ordersService.onProcess(id);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("ready/{id}")
    public ResponseEntity<OrdersTO> ready (@PathVariable Long id) {
        OrdersTO updatedOrder = ordersService.ready(id);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("delivered/{id}")
    public ResponseEntity<OrdersTO> delivered (@PathVariable Long id) {
        OrdersTO updatedOrder = ordersService.delivered(id);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ordersService.delete(id);
    }

}
