package de.dhbw.dinnerfortwo.impl.item;

import de.dhbw.dinnerfortwo.impl.restaurants.Restaurants;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    private String id;

    //@ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @Column(nullable = false)
//    private Restaurants restaurantsId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;




}
