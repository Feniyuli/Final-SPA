package de.dhbw.dinnerfortwo.impl.ordereditems;

import de.dhbw.dinnerfortwo.impl.orders.OrdersTO;
import de.dhbw.dinnerfortwo.impl.item.ItemsTO;

public class OrderedItemsTO {
    private long id;
    private int amount;
    private ItemsTO items;

    public OrderedItemsTO() {
    }

    public OrderedItemsTO(long id, int amount, ItemsTO items) {
        this.id = id;
        this.amount = amount;
        this.items = items;
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
}
