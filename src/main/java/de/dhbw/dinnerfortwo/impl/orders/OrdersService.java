package de.dhbw.dinnerfortwo.impl.orders;


import de.dhbw.dinnerfortwo.impl.ordereditems.OrderedItemsTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.NotSupportedException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public List<OrdersTO> getAllOrdersByRestaurantId(Long id){
        List<OrdersTO> getAllOrders = ((List<Orders>) ordersRepository.findAllOrders(id))
                .stream()
                .map(Orders::toDTO)
                .collect(Collectors.toList());

        return getAllOrders;
    }

    @Transactional
    public OrdersTO updateOrderIsPaid(Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Orders updatedOrder = order.get();
            if(updatedOrder.isPaid() == true){
                throw new EntityNotFoundException("paid Already");
            }
            updatedOrder.setPaid(true);
            updatedOrder = ordersRepository.save(updatedOrder);
            return updatedOrder.toDTO();
        } else {
            throw new NotFoundException("could not find order with id {" + id + "}.");
        }
    }

    @Transactional
    public float getTotalRevenue(Long id){
        List<OrdersTO> allOrders = getAllOrdersByRestaurantId(id);
        float revenue = 0;

        for(OrdersTO ordersTO: allOrders){
            if (ordersTO.isPaid() == true) {
                for (OrderedItemsTO orderedItemsTO : ordersTO.getOrderedItems()) {
                    revenue = revenue + (orderedItemsTO.getItems().getPrice()*orderedItemsTO.getAmount());
                }
            }
        }

        return revenue;
    }

    @Transactional
    public float getDailyRevenue(Long id, LocalDate date){
        List<OrdersTO> allOrders = getAllOrdersByRestaurantId(id);
        float revenue = 0;

        for(OrdersTO ordersTO: allOrders){
            LocalDate reservationDate = ordersTO.getReservation().getDate();

            if (ordersTO.isPaid() == true && reservationDate.equals(date)) {
                for (OrderedItemsTO orderedItemsTO : ordersTO.getOrderedItems()) {
                    revenue = revenue + (orderedItemsTO.getItems().getPrice()*orderedItemsTO.getAmount());
                }
            }
        }

        return revenue;
    }

    @Transactional
    public float getTotalOrder(long id){
        OrdersTO orders = getOrder(id);
        List<OrderedItemsTO> orderedItems = orders.getOrderedItems();
        float amount = 0;

        for (OrderedItemsTO orderedItemsTO : orders.getOrderedItems()) {
            amount = amount + (orderedItemsTO.getItems().getPrice()*orderedItemsTO.getAmount());
        }
        return amount;
    }

    @Transactional
    public List<OrdersTO> getOrderReserved(long id){
        List<OrdersTO> getAllOrders = ((List<Orders>) ordersRepository.findOrderReserved(id))
                .stream()
                .map(Orders::toDTO)
                .collect(Collectors.toList());

        return getAllOrders;
    }

    @Transactional
    public OrdersTO onProcess (Long id){
        Orders ordersById = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find order with Id " + id));
        OrdersTO order = getOrder(id);
        if(order.getOrderStatus() == OrderStatus.Open){
            order.setOrderStatus(OrderStatus.OnProcess);
        }
        Orders ordersToEntity = Orders.toEntity(order);
        Orders savedEntity = ordersRepository.save(ordersToEntity);
        return savedEntity.toDTO();
    }

    @Transactional
    public OrdersTO ready (Long id){
        Orders ordersById = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find order with Id " + id));
        OrdersTO order = getOrder(id);
        if(order.getOrderStatus() == OrderStatus.OnProcess){
            order.setOrderStatus(OrderStatus.Ready);
        }
        Orders ordersToEntity = Orders.toEntity(order);
        Orders savedEntity = ordersRepository.save(ordersToEntity);
        return savedEntity.toDTO();
    }

    @Transactional
    public OrdersTO delivered (Long id){
        Orders ordersById = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find order with Id " + id));
        OrdersTO order = getOrder(id);
        if(order.getOrderStatus() == OrderStatus.Ready){
            order.setOrderStatus(OrderStatus.Delivered);
        }
        Orders ordersToEntity = Orders.toEntity(order);
        Orders savedEntity = ordersRepository.save(ordersToEntity);
        return savedEntity.toDTO();
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting order with id {}", id);
        OrdersTO order = getOrder(id);

        if(order.isPaid() == false) {
            ordersRepository.deleteById(id);
        } else {
            throw new NotFoundException("It is paid, cant be deleted!");
        }
    }

}
