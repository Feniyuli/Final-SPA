package de.dhbw.dinnerfortwo.impl.item;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;


}
