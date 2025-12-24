package com.techacademy.grades.adapters.outubound.gateway.classroom

import com.techacademy.grades.adapters.outubound.gateway.dto.ClassroomResponseDTO
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "student-api")
@Path("/classrooms")
interface ClassroomClient {

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    fun search(
        @QueryParam("course") course: String?,
        @QueryParam("grade") grade: Int?,
    ): List<ClassroomResponseDTO>
}