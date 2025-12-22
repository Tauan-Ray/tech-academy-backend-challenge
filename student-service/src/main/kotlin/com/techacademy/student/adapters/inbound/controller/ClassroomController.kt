package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.usecase.classroom.FindAllClassroomsUseCase
import com.techacademy.student.application.usecase.classroom.FindClassroomByIdentityUseCase
import com.techacademy.student.application.usecase.classroom.FindClassroomUseCase
import jakarta.ws.rs.GET
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
        @QueryParam("year") year: Int,
        @QueryParam("course") course: String,
        @QueryParam("grade") grade: Int
    ): ClassroomDTO? {
        return findClassroomByIdentityUseCase
            .execute(year, course, grade)
    }
}