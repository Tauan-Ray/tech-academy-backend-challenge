package com.techacademy.student.adapters.inbound.exception

import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ClassroomNotExistsExceptionMapper: ExceptionMapper<ClassroomNotExistsException> {
    override fun toResponse(ex: ClassroomNotExistsException): Response {
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}