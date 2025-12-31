package com.techacademy.student.adapters.outbound.gateway

import com.techacademy.student.application.dto.GradeResponseDTO
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "grades-api")
@Path("/grades")
interface GradeClient {

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    fun findGradesByStudents(@QueryParam("studentIds") studentIds: String?): Map<Int, List<GradeResponseDTO>>
}