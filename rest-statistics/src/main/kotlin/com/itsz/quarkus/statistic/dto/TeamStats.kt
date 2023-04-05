package com.itsz.quarkus.statistic.dto

class TeamStats(
    private var villains: Int = 0,
    private var heroes: Int = 0
) {

    fun add(fight: Fight): Double {
        if (fight.winnerTeam.equals("heroes", true)) {
            heroes += 1
        } else {
            villains += 1
        }
        return heroes.toDouble().div((heroes + villains))
    }
}