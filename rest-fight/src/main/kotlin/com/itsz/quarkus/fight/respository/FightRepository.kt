package com.itsz.quarkus.fight.respository

import com.itsz.quarkus.fight.model.Fight
import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FightRepository: ReactivePanacheMongoRepository<Fight>