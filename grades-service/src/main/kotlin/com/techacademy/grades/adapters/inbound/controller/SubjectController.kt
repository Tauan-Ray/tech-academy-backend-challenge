package com.techacademy.grades.adapters.inbound.controller

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.usecase.subject.CreateSubjectUseCase
import com.techacademy.grades.application.usecase.subject.FindAllSubjectsUseCase
import com.techacademy.grades.application.usecase.subject.FindSubjectByNameUseCase
import com.techacademy.grades.application.usecase.subject.FindSubjectUseCase
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/subjects")
class SubjectController(
    private val findAllSubjectsUseCase: FindAllSubjectsUseCase,
    private val findSubjectUseCase: FindSubjectUseCase,
    private val findSubjectByNameUseCase: FindSubjectByNameUseCase,
    private val createSubjectUseCase: CreateSubjectUseCase,
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<SubjectDTO> {
        return findAllSubjectsUseCase
            .execute()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findSubject(@PathParam("id") id: Int): SubjectDTO? {
        return findSubjectUseCase
            .execute(id)
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    fun findStudentByEmail(@QueryParam("name") name: String): SubjectDTO? {
        return findSubjectByNameUseCase
            .execute(name)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(createSubject: CreateSubjectDTO): SubjectDTO {
        return createSubjectUseCase
            .execute(createSubject)
    }
}