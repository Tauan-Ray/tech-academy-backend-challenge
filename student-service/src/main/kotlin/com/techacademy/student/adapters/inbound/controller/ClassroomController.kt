package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.dto.CreateClassroomDTO
import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.usecase.classroom.CreateClassroomUseCase
import com.techacademy.student.application.usecase.classroom.FindAllClassroomsUseCase
import com.techacademy.student.application.usecase.classroom.FindClassroomByIdentityUseCase
import com.techacademy.student.application.usecase.classroom.FindClassroomUseCase
import com.techacademy.student.application.usecase.classroom.ListStudentsOfClassroomUseCase
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("classrooms")
class ClassroomController(
    private val findAllClassroomsUseCase: FindAllClassroomsUseCase,
    private val findClassroomUseCase: FindClassroomUseCase,
    private val findClassroomByIdentityUseCase: FindClassroomByIdentityUseCase,
    private val listStudentsOfClassroomUseCase: ListStudentsOfClassroomUseCase,
    private val createClassroomUseCase: CreateClassroomUseCase,
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): List<ClassroomDTO> {
        return findAllClassroomsUseCase
            .execute()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findClassroom(@PathParam("id") id: Int): ClassroomDTO? {
        return findClassroomUseCase
            .execute(id)
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    fun findClassroomByIdentity(
        @QueryParam("year") year: Int?,
        @QueryParam("course") course: String?,
        @QueryParam("grade") grade: Int?
    ): List<ClassroomDTO?> {
        return findClassroomByIdentityUseCase
            .execute(year, course, grade)
    }

    @GET
    @Path("/{id}/students")
    @Produces(MediaType.APPLICATION_JSON)
    fun listStudentsOfClassroom(@PathParam("id") id: Int): List<StudentDTO> {
        return listStudentsOfClassroomUseCase
            .execute(id)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createClassroom(createClassroom: CreateClassroomDTO): ClassroomDTO {
        return createClassroomUseCase
            .execute(createClassroom)
    }
}