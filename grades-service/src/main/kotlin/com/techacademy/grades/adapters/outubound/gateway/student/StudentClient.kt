package com.techacademy.grades.adapters.outubound.gateway.student

import com.techacademy.grades.adapters.outubound.gateway.dto.StudentResponseDTO
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "student-api")
@Path("/students")
interface StudentClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun exists(@PathParam("id") id: Int): StudentResponseDTO?
}