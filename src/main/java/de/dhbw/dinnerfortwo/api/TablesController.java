package de.dhbw.dinnerfortwo.api;

import de.dhbw.dinnerfortwo.impl.table.TablesService;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    //@GetMapping
    //public ResponseEntity<List<TablesTO>> getAllTables() {
    //    log.info("Get all tables");
    //    var result = tablesService.getAllTables();
    //    return new ResponseEntity<>(result, HttpStatus.OK);
    //}

    //@PostMapping
    //public ResponseEntity<TablesTO> createTable(@RequestBody TablesTO newTable) {
    //    TablesTO result = tablesService.create(newTable);
    //    log.info("Created table {}", result);
    //    return new ResponseEntity<>(result, HttpStatus.CREATED);
    //}
}
