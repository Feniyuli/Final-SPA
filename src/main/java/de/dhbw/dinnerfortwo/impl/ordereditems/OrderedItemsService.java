package de.dhbw.dinnerfortwo.impl.ordereditems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderedItemsService {

    private final OrderedItemsRepository orderedItemsRepository;
    public OrderedItemsService(OrderedItemsRepository orderedItemsRepository) {
        this.orderedItemsRepository = orderedItemsRepository;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public OrderedItemsTO getOrderedItem(long id) {
        log.info("Looking for an ordered item with id {}", id);
        OrderedItems orderedItemsById = orderedItemsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find ordered item with Id " + id));

        OrderedItemsTO getOrderedItemsById = orderedItemsById.toDTO();

        return getOrderedItemsById;
    }
    @Transactional
    public List<OrderedItemsTO> getAllOrderedItems() {
        log.info("Get all ordered Items");
        List<OrderedItemsTO> getAllOrderedItems = ((List<OrderedItems>) orderedItemsRepository.findAll())
                .stream()
                .map(OrderedItems::toDTO)
                .collect(Collectors.toList());

        return getAllOrderedItems;
    }
    @Transactional
    public OrderedItemsTO create(OrderedItemsTO orderedItemsTO) {
        log.info("Save or update ordered items {}", orderedItemsTO);

        OrderedItems orderedItemsToEntity = OrderedItems.toEntity(orderedItemsTO);
        OrderedItems savedEntity = orderedItemsRepository.save(orderedItemsToEntity);

        return savedEntity.toDTO();
    }

}
