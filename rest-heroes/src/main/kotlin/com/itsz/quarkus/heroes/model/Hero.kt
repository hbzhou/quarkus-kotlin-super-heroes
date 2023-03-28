package com.itsz.quarkus.heroes.model

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity
data class Hero(
    var name: String,
    var otherName: String,
    var level: Int,
    var picture: String,
    @Column(columnDefinition = "TEXT")
    var powers: String,
) : PanacheEntity() {

    companion object : PanacheCompanion<Hero>
}
