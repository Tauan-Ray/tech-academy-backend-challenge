package com.techacademy.grades.adapters.inbound.controller

import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.usecase.grade.CreateGradeUseCase
import com.techacademy.grades.application.usecase.grade.FindAllGradesUseCase
import com.techacademy.grades.application.usecase.grade.FindGradeByStudentUseCase
import com.techacademy.grades.application.usecase.grade.FindGradeUseCase
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/grades")
class GradeController(
    private val findAllGradesUseCase: FindAllGradesUseCase,
    private val findGradeUseCase: FindGradeUseCase,
    private val findGradeByStudentUseCase: FindGradeByStudentUseCase,
    private val createGradeUseCase: CreateGradeUseCase,
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<GradeDTO> {
        return findAllGradesUseCase
            .execute()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findGrade(@PathParam("id") id: Int): GradeDTO? {
        return findGradeUseCase
            .execute(id)
    }

    @GET
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findStudentByEmail(@PathParam("id") id: Int): GradeDTO? {
        return findGradeByStudentUseCase
            .execute(id)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(@Valid createGrade: CreateGradeDTO): GradeDTO {
        return createGradeUseCase
            .execute(createGrade)
    }
}