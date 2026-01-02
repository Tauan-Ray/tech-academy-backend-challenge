package com.techacademy.grades.adapters.inbound.exception

import com.techacademy.grades.application.service.exception.EnrollmentNotExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class EnrollmentNotExistsExceptionMapper: ExceptionMapper<EnrollmentNotExistsException> {
    override fun toResponse(ex: EnrollmentNotExistsException): Response {
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}