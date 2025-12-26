package com.techacademy.grades.adapters.inbound.exception

import com.techacademy.grades.application.service.exception.SubjectNotExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class SubjectNotExistsExceptionMapper: ExceptionMapper<SubjectNotExistsException> {
    override fun toResponse(ex: SubjectNotExistsException): Response {
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}