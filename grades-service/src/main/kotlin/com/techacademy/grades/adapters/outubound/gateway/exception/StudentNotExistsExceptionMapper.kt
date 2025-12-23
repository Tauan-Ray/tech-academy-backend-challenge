package com.techacademy.grades.adapters.outubound.gateway.exception

import com.techacademy.grades.application.service.exception.StudentNotExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class StudentNotExistsExceptionMapper: ExceptionMapper<StudentNotExistsException> {
    override fun toResponse(ex: StudentNotExistsException): Response {
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}