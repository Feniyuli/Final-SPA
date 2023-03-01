package de.dhbw.dinnerfortwo.impl.table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesService {

    private final TablesRepository tablesRepository;
    public TablesService(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public List<TablesTO> getAllTables() {
        log.info("Get all Tables");
        List<TablesTO> getAllTables = ((List<Tables>) tablesRepository.findAll())
                .stream()
                .map(Tables::toDTO)
                .collect(Collectors.toList());;

        return getAllTables;
    }
    @Transactional
    public TablesTO create(TablesTO tablesTO) {
        log.info("Save or update Tables {}", tablesTO);

        Tables tablesToEntity = Tables.toEntity(tablesTO);
        Tables savedEntity = tablesRepository.save(tablesToEntity);

        return savedEntity.toDTO();
    }
}
