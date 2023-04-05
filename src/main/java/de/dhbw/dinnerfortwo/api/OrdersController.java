package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsService;
import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import de.dhbw.dinnerfortwo.impl.orders.OrdersService;
import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final OrderedItemsService orderedItemsService;


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public OrdersController(OrdersService ordersService, OrderedItemsService orderedItemsService) {
        this.ordersService = ordersService;
        this.orderedItemsService = orderedItemsService;
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

    @GetMapping("ordereditems/{id}")
    public ResponseEntity<List<OrderedItemsTO>> getAllOrderedItemsByOrderId(@PathVariable long id) {
        log.info("Get ordered items with order id {}", id);
        try {
            var orderedItems = orderedItemsService.getAllOrderedItemsByOrderId(id);
            return ResponseEntity.ok(orderedItems);
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


    @PutMapping("payOrder/{id}")
    public ResponseEntity<OrdersTO> updateOrderIsPaid(@PathVariable Long id) {
        OrdersTO updatedOrder = ordersService.updateOrderIsPaid(id);
        return ResponseEntity.ok(updatedOrder);
    }
}