package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsService;
import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.OrderedItemsController.URI_ORDEREDITEMS_BASE;

/**
 * REST (HTTP) API of the Dinner app to interact with the UI or external applications.
 * The REST API provides the CRUD operations to create, read, update or delete an orderedItem
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_ORDEREDITEMS_BASE, produces = "application/json;charset=UTF-8")
public class OrderedItemsController {
    public static final String URI_ORDEREDITEMS_BASE = URI_BASE + "/ordereditems";

    private final OrderedItemsService orderedItemsService;

    public OrderedItemsController(OrderedItemsService orderedItemsService) {
        this.orderedItemsService = orderedItemsService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{id}")
    public ResponseEntity<OrderedItemsTO> getOrderedItem(@PathVariable long id) {
        log.info("Get ordered item with id {}", id);
        try {
            var orderedItem = orderedItemsService.getOrderedItem(id);
            return ResponseEntity.ok(orderedItem);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderedItemsTO>> getAllOrderedItems() {
        log.info("Get all ordered items");
        var result = orderedItemsService.getAllOrderedItems();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderedItemsTO> createOrderedItem(@RequestBody OrderedItemsTO newOrderedItem) {
        OrderedItemsTO result = orderedItemsService.create(newOrderedItem);
        log.info("Created ordered item {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
