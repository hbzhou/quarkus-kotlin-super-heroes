package com.itsz.quarkus.heroes.resource

import com.itsz.quarkus.heroes.model.Hero
import com.itsz.quarkus.heroes.service.HeroService
import io.smallrye.mutiny.Uni
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Path("/api/heroes")
class HeroResource(val heroService: HeroService) {


    @GET
    fun getAll(): Uni<Response> = heroService.getAll().onItem().transform { Response.ok(it).build() }

    @GET
    @Path("/{id}")
    fun getById(@RestPath id: Long): Uni<Response> = heroService.getById(id).onItem().transform { Response.ok(it).build() }

    @POST
    fun createHero (hero: Hero):Uni<Response> = heroService.create(hero).onItem().transform { Response.ok(it).build() }

}