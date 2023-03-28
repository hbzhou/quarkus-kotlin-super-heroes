package com.itsz.quarkus.heroes.service

import com.itsz.quarkus.heroes.model.Hero

class HeroService {

    fun getAll() = Hero.findAll().list()

    fun getById(id: Long) = Hero.findById(id)

    fun create(hero: Hero) = hero.persist<Hero>()

}
