package com.itsz.quarkus.fight.model

import io.quarkus.mongodb.panache.common.MongoEntity
import java.time.Instant

@MongoEntity(collection = "fights")
data class Fight(
    var fightDate: Instant?= Instant.now(),
    var winnerName: String? =null,
    var winnerLevel: Int? = 0,
    var winnerPicture: String? = null,
    var loserName: String? = null,
    var loserLevel: Int? = 0 ,
    var loserPicture: String? = null,
    var winnerTeam: String? = null,
    var loserTeam: String? = null
)
