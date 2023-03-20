package de.dhbw.dinnerfortwo.impl.item;

import de.dhbw.dinnerfortwo.impl.reservation.Reservation;
import de.dhbw.dinnerfortwo.impl.reservation.ReservationTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;
import de.dhbw.dinnerfortwo.impl.item.ItemsTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    private final ItemsRepository itemsRepository;
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public ItemsTO getItem(long id) {
        log.info("Looking for an item with id {}", id);
        Items ItemsById = itemsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find item with Id " + id));

        ItemsTO getItemsById = ItemsById.toDTO();

        return getItemsById;
    }
    @Transactional
    public List<ItemsTO> getAllItems() {
        log.info("Get all Items");
        List<ItemsTO> getAllItems = itemsRepository.findAll()
                .stream()
                .map(Items::toDTO)
                .collect(Collectors.toList());

        return getAllItems;
    }
    @Transactional
    public ItemsTO create(ItemsTO itemsTO) {
        log.info("Save or update Items {}", itemsTO);

        Items itemsToEntity = Items.toEntity(itemsTO);
        Items savedEntity = itemsRepository.save(itemsToEntity);

        return savedEntity.toDTO();
    }

    @Transactional
    public List<ItemsTO> getAllItemByRestaurantId(Long id) {
        log.info("Get all Items by Restaurant Id");
        List<ItemsTO> getAllItem = ((List<Items>) itemsRepository.findAllItemsByRestaurantId(id))
                .stream()
                .map(Items::toDTO)
                .collect(Collectors.toList());

        return getAllItem;
    }
}
