package com.itsz.quarkus.villains.resource

import com.itsz.quarkus.villains.service.VillainDto
import com.itsz.quarkus.villains.service.VillainService
import org.jboss.resteasy.reactive.RestPath
import javax.enterprise.context.ApplicationScoped
import javax.validation.Valid
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.core.Response

@Path("/api/villains")
@ApplicationScoped
class VillainResource(val villainService: VillainService) {

    @GET
    fun getAll(): Response = Response.ok(villainService.findAll()).build()

    @GET
    @Path("/{id}")
    fun getById(@RestPath id: Long): Response {
        val villain = villainService.findById(id)
        return if (villain != null) Response.ok(villain).build() else Response.noContent().build()
    }

    @POST
    fun create(@Valid villainDto: VillainDto): Response = Response.ok(villainService.persist(villainDto)).build()

    @PUT
    fun update(@Valid villainDto: VillainDto): Response = Response.ok(villainService.update(villainDto)).build()

    @DELETE
    @Path("/{id}")
    fun delete(@RestPath id: Long): Response {
        villainService.delete(id)
        return Response.noContent().build()
    }


}