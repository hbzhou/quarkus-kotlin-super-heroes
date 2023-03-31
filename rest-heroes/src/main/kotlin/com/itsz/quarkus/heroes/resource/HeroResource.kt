package com.itsz.quarkus.heroes.resource

import com.itsz.quarkus.heroes.model.Hero
import com.itsz.quarkus.heroes.service.HeroService
import io.smallrye.mutiny.Uni
import org.jboss.resteasy.reactive.RestPath
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.*
import javax.ws.rs.core.Response

@Path("/api/heroes")
@ApplicationScoped
class HeroResource(val heroService: HeroService) {

    @GET
    fun getAll(): Uni<Response> = heroService.getAll().onItem().transform { Response.ok(it).build() }
    @GET
    @Path("/{id}")
    fun getById(@RestPath id: Long): Uni<Response> = heroService.getById(id)
        .onItem()
        .transform {
            if (it != null) {
                Response.ok(it).build()
            } else {
                Response.status(404).build()
            }
        }

    @GET
    @Path("/random")
    fun getRandomHero(): Uni<Response> = heroService.getRandomHero().onItem().transform { Response.ok(it).build() }

    @POST
    fun createHero(hero: Hero): Uni<Response> = heroService.create(hero).onItem().transform { Response.ok(it).build() }
    @PUT
    fun updateHero(hero: Hero): Uni<Response> = heroService.update(hero).onItem().transform { Response.ok(it).build() }
    @DELETE
    @Path("/{id}")
    fun delete(@RestPath id: Long): Uni<Response> = heroService.delete(id).onItem().transform { Response.noContent().build() }


}