package de.dhbw.dinnerfortwo.impl.ordereditems;

import de.dhbw.dinnerfortwo.impl.item.Items;
import de.dhbw.dinnerfortwo.impl.orders.Orders;
import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import de.dhbw.dinnerfortwo.impl.restaurants.RestaurantTO;
import de.dhbw.dinnerfortwo.impl.table.ItemsTO;

public class OrderedItemsTO {
    private long id;
    private int amount;
    private ItemsTO items;
    private OrdersTO orders;

    public OrderedItemsTO() {
    }

    public OrderedItemsTO(long id, int amount, ItemsTO items, OrdersTO orders) {
        this.id = id;
        this.amount = amount;
        this.items = items;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ItemsTO getItems(){return items;}

    public void setItems(ItemsTO items) { this.items = items;
    }

    public OrdersTO getOrders() {return orders;
    }
    public void setOrders(OrdersTO orders) { this.orders = orders;
    }
}
