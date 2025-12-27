package com.techacademy.student.adapters.inbound.exception

import com.techacademy.student.application.service.exception.EnrollmentAlreadyExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class EnrollmentAlreadyExistsExceptionMapper: ExceptionMapper<EnrollmentAlreadyExistsException> {
    override fun toResponse(ex: EnrollmentAlreadyExistsException): Response {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}