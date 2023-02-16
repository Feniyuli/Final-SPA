package de.dhbw.dinnerfortwo.impl


import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Restaurant(
    @Id
//    @GeneratedValue // Auto generated IDs are contradictionary to functional programming. Better control the id yourself
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val cuisine: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val rating: Double
)