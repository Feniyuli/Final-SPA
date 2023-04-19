package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import de.dhbw.dinnerfortwo.impl.table.TablesService;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.dhbw.dinnerfortwo.api.MetaInfo.URI_BASE;
import static de.dhbw.dinnerfortwo.api.TablesController.URI_TABLES_BASE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = URI_TABLES_BASE, produces = "application/json;charset=UTF-8")
public class TablesController {
    public static final String URI_TABLES_BASE = URI_BASE + "/tables";

    private final TablesService tablesService;

    public TablesController(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public ResponseEntity<List<TablesTO>> getAllTables() {
        log.info("Get all tables");
        var result = tablesService.getAllTables();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TablesTO> getTable(@PathVariable long id) {
        log.info("Get Orders with id {}", id);
        try {
            var table = tablesService.getTable(id);
            return ResponseEntity.ok(table);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TablesTO> createTable(@RequestBody TablesTO newTable) {
        TablesTO result = tablesService.create(newTable);
        log.info("Created table {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TablesTO> updateOwner(@PathVariable Long id, @RequestBody TablesTO tablesTO) {
        TablesTO result = tablesService.updateTable(id, tablesTO);
        log.info("updated table {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        tablesService.delete(id);
    }

}
