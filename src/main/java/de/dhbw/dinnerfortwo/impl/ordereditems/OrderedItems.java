package de.dhbw.dinnerfortwo.impl.ordereditems;

import de.dhbw.dinnerfortwo.impl.item.Items;
import de.dhbw.dinnerfortwo.impl.orders.Orders;
import de.dhbw.dinnerfortwo.impl.item.ItemsTO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderedItems {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "items", referencedColumnName = "id")
    private Items items;

    public OrderedItems() {
    }

    public OrderedItems(long id, int amount, Items items) {
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

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedItems)) return false;
        Orders orders = (Orders) o;
        return getId() == orders.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public OrderedItemsTO toDTO(){
        OrderedItemsTO orderedItemsTO = new OrderedItemsTO();
        BeanUtils.copyProperties( this, orderedItemsTO);
        Items items = this.getItems();
        ItemsTO itemTO = items.toDTO();

        orderedItemsTO.setItems(itemTO);

        return orderedItemsTO;
    }

    public static OrderedItems toEntity(OrderedItemsTO orderedItemsTO){
        OrderedItems orderedItemsToEntity = new OrderedItems();
        BeanUtils.copyProperties(orderedItemsTO, orderedItemsToEntity);
        ItemsTO itemTO = orderedItemsTO.getItems();
        Items items = Items.toEntity(itemTO);

        orderedItemsToEntity.setItems(items);

        return orderedItemsToEntity;
    }
}
