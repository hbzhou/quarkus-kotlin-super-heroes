package com.itsz.quarkus.statistic.service

import com.itsz.quarkus.statistic.dto.Fight
import io.smallrye.mutiny.Multi
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

data class Score(
    val name: String,
    val score: Int? = 0,
)


@ApplicationScoped
class StatisticService {

    @Incoming("fights")
    @Outgoing("team-stats")
    fun computeTeamStats(results: Multi<Fight>): Multi<Double> {
        return results.onItem().transform {
            println("receiving message from kafka topic fights $it")
            it.loserLevel.times(0.1)
        }

    }

    @Incoming("fights")
    @Outgoing("winner-stats")
    fun computeTopWinners(results: Multi<Fight>): Multi<List<Score>> {
      return Multi.createFrom().item(emptyList())
    }


}