package de.dhbw.dinnerfortwo.impl.table;

import de.dhbw.dinnerfortwo.impl.reservation.ReservationService;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesService {

    private final TablesRepository tablesRepository;
    private final ReservationService reservationService;

    public TablesService(TablesRepository tablesRepository, ReservationService reservationService) {
        this.tablesRepository = tablesRepository;
        this.reservationService = reservationService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public TablesTO getTable(long id) {
        log.info("Looking for a table with id {}", id);
        Tables TablesById = tablesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find table with Id " + id));

        TablesTO getTablesById = TablesById.toDTO();
        return getTablesById;
    }

    @Transactional
    public List<TablesTO> getAllTables() {
        log.info("Get all Tables");
        List<TablesTO> getAllTables = tablesRepository.findAll()
                .stream()
                .map(Tables::toDTO)
                .collect(Collectors.toList());

        return getAllTables;
    }
    @Transactional
    public TablesTO create(TablesTO tablesTO) {
        log.info("Save Tables {}", tablesTO);

        Tables tablesToEntity = Tables.toEntity(tablesTO);
        Tables savedEntity = tablesRepository.save(tablesToEntity);

        return savedEntity.toDTO();
    }

    @Transactional
    public List<TablesTO> getTablesByRestaurantId(Long id) {
        log.info("Get all Tables in Restaurant");
        List<TablesTO> getAllTables = ((List<Tables>) tablesRepository.findAllTablesByRestaurantId(id))
                .stream()
                .map(Tables::toDTO)
                .collect(Collectors.toList());

        return getAllTables;
    }

    @Transactional
    public List<TablesTO> getAvailableTables(Long id, LocalDate localDate){
        List <TablesTO> tables = getTablesByRestaurantId(id);
        List <ReservationTO> reservation = reservationService.getAllReservationByRestaurantId(id);
        List <TablesTO> takenTables = new ArrayList<>();
        List <TablesTO> availableTable = new ArrayList<>(tables);

        for(ReservationTO reservationTO : reservation){
            if(reservationTO.getDate().equals(localDate)) {
                takenTables.add(reservationTO.getTable());
            }
        }

        for (TablesTO tablesTO: tables){
            for(TablesTO taken : takenTables){
                if(tablesTO.getId() == taken.getId()){
                    availableTable.remove(tablesTO);
                    break;
                }
            }
        }

        return availableTable;
    }

    @Transactional
    public TablesTO updateTable (Long id, TablesTO tablesTO){
        Tables tableEntity = tablesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find table"));
        tableEntity.setTableNumber(tablesTO.getTableNumber());
        tableEntity.setCapacity(tablesTO.getCapacity());
        Tables savedEntity = tablesRepository.save(tableEntity);
        return savedEntity.toDTO();
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting table with id {}", id);
        tablesRepository.deleteById(id);
    }

}
