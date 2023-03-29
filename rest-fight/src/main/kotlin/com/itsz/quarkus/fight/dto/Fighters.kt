package com.itsz.quarkus.fight.dto

import javax.validation.constraints.NotNull

data class Fighters(
    @NotNull
    var hero: Hero,
    @NotNull
    var villain: Villain
)

data class Hero(
    var name: String,
    var level: Int,
    var picture: String,
    var powers: String
)

data class Villain(
    var name: String,
    var level: Int,
    var picture: String,
    var powers: String
)

