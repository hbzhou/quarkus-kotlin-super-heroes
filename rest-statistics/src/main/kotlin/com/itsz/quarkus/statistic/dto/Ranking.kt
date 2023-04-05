package com.itsz.quarkus.statistic.dto

import com.itsz.quarkus.statistic.service.Score
import java.util.*
import kotlin.Comparator


class Ranking(private val max: Int) {
    private val comparator = Comparator.comparingInt<Score> { it.score*-1  }
    private val top = LinkedList<Score>()

    fun onNewScore(score: Score): List<Score> {
        // Remove score if already present,
        top.removeIf { it.name.equals(score.name, true) }
        // Add the score
        top.add(score)
        // Sort
        top.sortWith(comparator)

        // Drop on overflow
        if (top.size > max) {
            top.remove(top.last)
        }
        return Collections.unmodifiableList(top)
    }
}