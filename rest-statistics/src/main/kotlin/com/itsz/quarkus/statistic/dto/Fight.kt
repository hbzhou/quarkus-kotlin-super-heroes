package com.itsz.quarkus.statistic.dto

import io.quarkus.runtime.annotations.RegisterForReflection
import java.time.Instant


@RegisterForReflection
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
