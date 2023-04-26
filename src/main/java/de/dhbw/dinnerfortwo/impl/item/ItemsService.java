package de.dhbw.dinnerfortwo.impl.item;

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
        log.info("create item: ", itemsTO);

        Items itemsToEntity = Items.toEntity(itemsTO);
        Items savedEntity = itemsRepository.save(itemsToEntity);

        return savedEntity.toDTO();
    }

    @Transactional
    public List<ItemsTO> getAllItemByRestaurantId(Long id) {
        log.info("Get all Items in a Restaurant");
        List<ItemsTO> getAllItem = ((List<Items>) itemsRepository.findAllItemsByRestaurantId(id))
                .stream()
                .map(Items::toDTO)
                .collect(Collectors.toList());

        return getAllItem;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting item with id {}", id);
        itemsRepository.deleteById(id);
    }
}
