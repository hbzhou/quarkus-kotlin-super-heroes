package com.itsz.quarkus.fight.model

import io.quarkus.mongodb.panache.common.MongoEntity
import java.time.Instant

@MongoEntity(collection = "fights")
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
