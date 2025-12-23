package com.techacademy.student.adapters.inbound.exception

import com.techacademy.student.application.service.exception.ClassroomAlreadyExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ClassroomAlreadyExistsExceptionMapper: ExceptionMapper<ClassroomAlreadyExistsException> {
    override fun toResponse(ex: ClassroomAlreadyExistsException): Response {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}