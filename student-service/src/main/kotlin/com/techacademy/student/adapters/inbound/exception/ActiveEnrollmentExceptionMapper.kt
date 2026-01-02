package com.techacademy.student.adapters.inbound.exception

import com.techacademy.student.application.service.exception.ActiveEnrollmentException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ActiveEnrollmentExceptionMapper: ExceptionMapper<ActiveEnrollmentException> {
    override fun toResponse(ex: ActiveEnrollmentException): Response {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}