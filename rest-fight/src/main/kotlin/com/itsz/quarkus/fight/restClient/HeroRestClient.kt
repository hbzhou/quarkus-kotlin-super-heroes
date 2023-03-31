package com.itsz.quarkus.fight.restClient

import com.itsz.quarkus.fight.dto.Hero
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/api/heroes")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
interface HeroRestClient {

    @GET
    @Path("/random")
    fun findRandomHero(): Hero
}