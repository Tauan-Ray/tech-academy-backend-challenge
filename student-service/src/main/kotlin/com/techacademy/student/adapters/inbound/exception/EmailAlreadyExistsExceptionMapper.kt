package com.techacademy.student.adapters.inbound.exception

import com.techacademy.student.application.service.exception.EmailAlreadyExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class EmailAlreadyExistsExceptionMapper: ExceptionMapper<EmailAlreadyExistsException> {
    override fun toResponse(ex: EmailAlreadyExistsException): Response {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}