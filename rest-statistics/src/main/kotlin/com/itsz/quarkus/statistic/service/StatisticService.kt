package com.itsz.quarkus.statistic.service

import com.itsz.quarkus.statistic.dto.Fight
import com.itsz.quarkus.statistic.dto.Ranking
import com.itsz.quarkus.statistic.dto.TeamStats
import io.quarkus.runtime.annotations.RegisterForReflection
import io.smallrye.mutiny.Multi
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@RegisterForReflection
data class Score(
    var name: String? = null,
    var score: Int = 0,
)


@ApplicationScoped
class StatisticService(val logger: Logger) {
    private val stats: TeamStats = TeamStats()
    private val topWinners: Ranking = Ranking(10)


    @Incoming("fights")
    @Outgoing("team-stats")
    fun computeTeamStats(results: Multi<Fight>): Multi<Double> {
        return results
            .onItem().transform(stats::add)
            .invoke { _ -> logger.info("Fight received. Computed the team statistics") }

    }

    @Incoming("fights")
    @Outgoing("winner-stats")
    fun computeTopWinners(results: Multi<Fight>): Multi<List<Score>> {
        return results
            .group().by { it.winnerName }
            .onItem().transformToMultiAndMerge { group ->group.onItem().scan({ Score() }, ::incrementScore)}
            .onItem().transform(topWinners::onNewScore)
            .invoke { _ -> logger.info("Fight received. Computed the top winners") }
    }

    private fun incrementScore(score: Score, fight: Fight): Score {
        score.name = fight.winnerName
        score.score++
        return score
    }


}