package com.itsz.quarkus.fight.restClient

import com.itsz.quarkus.fight.dto.Villain
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/api/villains")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
interface VillainRestClient {

    @GET
    @Path("/random")
    fun findRandomVillain():Villain

}