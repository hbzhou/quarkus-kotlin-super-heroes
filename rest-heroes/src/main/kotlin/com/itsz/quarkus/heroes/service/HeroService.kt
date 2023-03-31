package com.itsz.quarkus.heroes.service

import com.itsz.quarkus.heroes.model.Hero
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.BadRequestException
import kotlin.random.Random

@ApplicationScoped
class HeroService {

    fun getAll() = Hero.findAll().list()

    fun getById(id: Long): Uni<Hero?> = Hero.findById(id)

    @ReactiveTransactional
    fun create(hero: Hero) = hero.persist<Hero>()

    fun getRandomHero(): Uni<Hero?> {
        val random = (1..3).random()
        return Hero.findById(random.toLong())
    }

   @ReactiveTransactional
    fun update(hero: Hero): Uni<Hero?> = Hero.findById(id = hero.id!!)
            .onItem().ifNull()
            .failWith { BadRequestException() }
            .onItem()
            .transform {
                it?.let {
                    it.name = hero.name
                    it.otherName = hero.otherName
                    it.picture = hero.picture
                    it.level = hero.level
                    it.powers = hero.powers
                }
                it
            }

    @ReactiveTransactional
    fun delete(id: Long) = Hero.deleteById(id)
}

