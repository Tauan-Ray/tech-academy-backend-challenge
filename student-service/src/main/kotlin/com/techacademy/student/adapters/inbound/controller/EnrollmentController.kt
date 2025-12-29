package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.application.dto.CreateEnrollmentDTO
import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.application.usecase.enrollment.CreateEnrollmentUseCase
import com.techacademy.student.application.usecase.enrollment.ExistsByStudentAndClassroomUseCase
import com.techacademy.student.application.usecase.enrollment.FindAllEnrollmentsUseCase
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByClassroomUseCase
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByIdUseCase
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByStudentUseCase
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/enrollments")
class EnrollmentController(
    private val findAllEnrollmentsUseCase: FindAllEnrollmentsUseCase,
    private val findEnrollmentByIdUseCase: FindEnrollmentByIdUseCase,
    private val findEnrollmentByStudentUseCase: FindEnrollmentByStudentUseCase,
    private val findEnrollmentByClassroomUseCase: FindEnrollmentByClassroomUseCase,
    private val existsByStudentAndClassroomUseCase: ExistsByStudentAndClassroomUseCase,
    private val createEnrollmentUseCase: CreateEnrollmentUseCase,
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<EnrollmentDTO> {
        return findAllEnrollmentsUseCase
            .execute()
    }

    @GET
    @Path("/{enrollmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findEnrollmentById(@PathParam("enrollmentId") enrollmentId: Int): EnrollmentDTO? {
        return findEnrollmentByIdUseCase
            .execute(enrollmentId)
    }

    @GET
    @Path("/student/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findEnrollmentByStudent(@PathParam("studentId") studentId: Int): List<EnrollmentDTO> {
        return findEnrollmentByStudentUseCase
            .execute(studentId)
    }

    @GET
    @Path("/classroom/{classroomId}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findEnrollmentByClassroom(@PathParam("classroomId") classroomId: Int): List<EnrollmentDTO> {
        return findEnrollmentByClassroomUseCase
            .execute(classroomId)
    }

    @GET
    @Path("/exists")
    fun existsEnrollment(
        @QueryParam("studentId") studentId: Int,
        @QueryParam("classroomId") classroomId: Int
    ): Boolean {
        return existsByStudentAndClassroomUseCase
            .execute(studentId, classroomId)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createClassroom(@Valid createEnrollment: CreateEnrollmentDTO): EnrollmentDTO {
        return createEnrollmentUseCase
            .execute(createEnrollment)
    }
}