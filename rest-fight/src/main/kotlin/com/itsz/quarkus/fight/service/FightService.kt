package com.itsz.quarkus.fight.service

import com.itsz.quarkus.fight.dto.Fighters
import com.itsz.quarkus.fight.model.Fight
import com.itsz.quarkus.fight.respository.FightRepository
import io.smallrye.mutiny.Uni
import org.bson.types.ObjectId
import org.jboss.logging.Logger
import java.time.Instant
import java.util.Random
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional


@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
class FightService(val fightRepository: FightRepository, val logger: Logger) {

    fun findAll() = fightRepository.findAll().list()

    fun findById(id: String) = fightRepository.findById(ObjectId(id))

    fun persistFight(fighters: Fighters): Uni<Fight> {
        val heroAdjust: Int = Random().nextInt(20)
        val villainAdjust: Int = Random().nextInt(20)

        val fight = if (fighters.hero.level + heroAdjust > fighters.villain.level + villainAdjust) {
            heroWon(fighters)
        } else if (fighters.hero.level < fighters.villain.level) {
            villainWon(fighters)
        } else {
            if (Random().nextBoolean()) heroWon(fighters) else villainWon(fighters)
        }

        return fightRepository.persist(fight)

    }

    private fun heroWon(fighters: Fighters): Fight {
        logger.info("Yes, Hero won :o)")
        return Fight(
            winnerName = fighters.hero.name,
            winnerPicture = fighters.hero.picture,
            winnerLevel = fighters.hero.level,
            loserName = fighters.villain.name,
            loserPicture = fighters.villain.picture,
            loserLevel = fighters.villain.level,
            winnerTeam = "heroes",
            loserTeam = "villains",
            fightDate = Instant.now()
        )
    }

    private fun villainWon(fighters: Fighters): Fight {
        logger.info("Gee, Villain won :o(")
        return Fight(
            winnerName = fighters.villain.name,
            winnerPicture = fighters.villain.picture,
            winnerLevel = fighters.villain.level,
            loserName = fighters.hero.name,
            loserPicture = fighters.hero.picture,
            loserLevel = fighters.hero.level,
            winnerTeam = "villains",
            loserTeam = "heroes",
            fightDate = Instant.now()
        )
    }


}