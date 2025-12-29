package com.techacademy.grades.adapters.outubound.gateway.enrollment

import com.techacademy.grades.adapters.outubound.gateway.dto.EnrollmentResponseDTO
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "student-api")
@Path("/enrollments")
interface EnrollmentClient {

    @GET
    @Path("/student/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findEnrollmentsByStudent(@PathParam("studentId") studentId: Int): List<EnrollmentResponseDTO>
}