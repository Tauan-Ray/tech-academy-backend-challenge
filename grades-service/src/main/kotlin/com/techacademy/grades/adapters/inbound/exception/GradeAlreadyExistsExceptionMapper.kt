package com.techacademy.grades.adapters.inbound.exception

import com.techacademy.grades.application.service.exception.GradeAlreadyExistsException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GradeAlreadyExistsExceptionMapper: ExceptionMapper<GradeAlreadyExistsException> {
    override fun toResponse(ex: GradeAlreadyExistsException): Response {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}