package de.dhbw.dinnerfortwo.impl.orders;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public OrdersTO getOrder(long id) {
        log.info("Looking for an order with id {}", id);
        Orders ordersById = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find order with Id " + id));

        OrdersTO getOrdersById = ordersById.toDTO();

        return getOrdersById;
    }
    @Transactional
    public List<OrdersTO> getAllOrders() {
        log.info("Get all Orders");
        List<OrdersTO> getAllOrders = ordersRepository.findAll()
                .stream()
                .map(Orders::toDTO)
                .collect(Collectors.toList());;

        return getAllOrders;
    }
    @Transactional
    public OrdersTO create(OrdersTO ordersTO) {
        log.info("Save or update order {}", ordersTO);

        Orders ordersToEntity = Orders.toEntity(ordersTO);
        Orders savedEntity = ordersRepository.save(ordersToEntity);

        return savedEntity.toDTO();
    }
}
