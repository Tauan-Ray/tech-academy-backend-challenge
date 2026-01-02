package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.application.dto.student.CreateStudentDTO
import com.techacademy.student.application.dto.student.ReportCardDTO
import com.techacademy.student.application.dto.student.StudentDTO
import com.techacademy.student.application.usecase.student.CreateStudentUseCase
import com.techacademy.student.application.usecase.student.FindAllByIdsUseCase
import com.techacademy.student.application.usecase.student.FindAllStudentsUseCase
import com.techacademy.student.application.usecase.student.FindReportsCardsUseCase
import com.techacademy.student.application.usecase.student.FindStudentByEmailUseCase
import com.techacademy.student.application.usecase.student.FindStudentUseCase
import jakarta.validation.Valid
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/students")
class StudentController(
    private val findAllStudentsUseCase: FindAllStudentsUseCase,
    private val findAllByIdsUseCase: FindAllByIdsUseCase,
    private val findStudentUseCase: FindStudentUseCase,
    private val findStudentByEmailUseCase: FindStudentByEmailUseCase,
    private val createStudentUseCase: CreateStudentUseCase,
    private val findReportsCardsUseCase: FindReportsCardsUseCase
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<StudentDTO> {
        return findAllStudentsUseCase
            .execute()
    }

    @GET
    @Path("/by-ids")
    @Produces(MediaType.APPLICATION_JSON)
    fun findAllByIds(@QueryParam("studentIds") studentIds: String?): List<StudentDTO> {
        if (studentIds.isNullOrBlank()) throw BadRequestException("StudentId é obrigatório!")

        val ids = try {
            studentIds.split(",").map { it.trim().toInt() }
        } catch (ex: NumberFormatException) {
            throw BadRequestException("StudentIds deve ser uma lista de números inteiros!")
        }

        return findAllByIdsUseCase
            .execute(studentIds = ids)
    }


    @GET
    @Path("/report-card")
    @Produces(MediaType.APPLICATION_JSON)
    fun generateReportCard(@QueryParam("studentIds") studentIds: String?): List<ReportCardDTO> {
        if (studentIds.isNullOrBlank()) throw BadRequestException("StudentId é obrigatório!")

        val ids = try {
            studentIds.split(",").map { it.trim().toInt() }
        } catch (ex: NumberFormatException) {
            throw BadRequestException("StudentIds deve ser uma lista de números inteiros!")
        }

        return findReportsCardsUseCase
            .execute(studentIds = ids)
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findStudent(@PathParam("id") id: Int): StudentDTO? {
        return findStudentUseCase
            .execute(id)
    }

    @GET
    @Path("/email")
    @Produces(MediaType.APPLICATION_JSON)
    fun findStudentByEmail(@QueryParam("email") email: String): StudentDTO? {
        return findStudentByEmailUseCase
            .execute(email)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(@Valid createStudent: CreateStudentDTO): StudentDTO {
        return createStudentUseCase
            .execute(createStudent)
    }
}