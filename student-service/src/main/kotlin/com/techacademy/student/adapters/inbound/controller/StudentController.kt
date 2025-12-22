package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.usecase.student.FindAllStudentsUseCase
import com.techacademy.student.application.usecase.student.FindStudentByEmailUseCase
import com.techacademy.student.application.usecase.student.FindStudentUseCase
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/students")
class StudentController(
    private val findAllStudentsUseCase: FindAllStudentsUseCase,
    private val findStudentUseCase: FindStudentUseCase,
    private val findStudentByEmailUseCase: FindStudentByEmailUseCase
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<StudentDTO> {
        return findAllStudentsUseCase
            .execute()
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
}