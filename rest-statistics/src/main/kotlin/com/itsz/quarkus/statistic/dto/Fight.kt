package com.itsz.quarkus.statistic.dto

import java.time.Instant

data class Fight(
    var fightDate: Instant,
    var winnerName: String,
    var winnerLevel: Int,
    var winnerPicture: String,
    var loserName: String,
    var loserLevel: Int,
    var loserPicture: String,
    var winnerTeam: String,
    var loserTeam: String
)
