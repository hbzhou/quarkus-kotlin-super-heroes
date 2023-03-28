package com.itsz.quarkus.villains.model

import javax.persistence.*

@Entity
data class Villain(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var name: String,
    var otherName: String,
    var level: Int,
    var picture: String,
    @Column(columnDefinition = "TEXT")
    var powers: String,
)
