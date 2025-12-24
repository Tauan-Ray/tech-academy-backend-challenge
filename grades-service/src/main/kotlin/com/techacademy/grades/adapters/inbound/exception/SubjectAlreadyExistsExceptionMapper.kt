package com.techacademy.grades.adapters.inbound.exception

import com.techacademy.grades.application.service.exception.SubjectAlreadyExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class SubjectAlreadyExistsExceptionMapper: ExceptionMapper<SubjectAlreadyExistsException> {
    override fun toResponse(ex: SubjectAlreadyExistsException): Response {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}