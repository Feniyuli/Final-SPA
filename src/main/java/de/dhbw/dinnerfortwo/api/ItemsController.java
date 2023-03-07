package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.item.ItemsService;
import de.dhbw.dinnerfortwo.impl.item.ItemsTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.ItemsController.URI_ITEMS_BASE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_ITEMS_BASE, produces = "application/json;charset=UTF-8")
public class ItemsController {
    public static final String URI_ITEMS_BASE = URI_BASE + "/items";

    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{id}")
    public ResponseEntity<ItemsTO> getItem(@PathVariable long id) {
        log.info("Get item with id {}", id);
        try {
            var item = itemsService.getItem(id);
            return ResponseEntity.ok(item);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<ItemsTO>> getAllItems() {
        log.info("Get all items");
        var result = itemsService.getAllItems();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemsTO> createItem(@RequestBody ItemsTO newItem) {
        ItemsTO result = itemsService.create(newItem);
        log.info("Created item {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
