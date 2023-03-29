package com.itsz.quarkus.fight.resource

import com.itsz.quarkus.fight.service.FightService
import io.smallrye.mutiny.Uni
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.validation.Valid
import javax.ws.rs.BadRequestException
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response
import com.itsz.quarkus.fight.dto.Fighters


@Path("/api/fights")
@ApplicationScoped
class FightResource(val fightService: FightService, val logger: Logger) {

    @GET
    fun findAll(): Uni<Response> = fightService.findAll().onItem().transform { Response.ok(it).build() }

    @GET
    @Path("/{id}")
    fun findById(id: String): Uni<Response> = fightService.findById(id)
        .onItem().ifNull()
        .failWith(BadRequestException())
        .onItem().ifNotNull()
        .transform { it?.let { Response.ok(it).build() } }


    @POST
    fun fight(@Valid fighters: Fighters) = fightService.persistFight(fighters)


}