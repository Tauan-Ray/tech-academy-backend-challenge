package com.techacademy.grades.adapters.inbound.exception

import com.techacademy.grades.application.service.exception.InvalidSubjectRuleException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class InvalidSubjectRuleExceptionMapper: ExceptionMapper<InvalidSubjectRuleException> {
    override fun toResponse(ex: InvalidSubjectRuleException): Response {
        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}