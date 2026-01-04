package com.techacademy.student.adapters.inbound.exception

import com.techacademy.student.application.service.exception.MissingDataUpdateStudentException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class MissingDataUpdateStudentExceptionMapper: ExceptionMapper<MissingDataUpdateStudentException> {
    override fun toResponse(ex: MissingDataUpdateStudentException): Response {
        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(mapOf("message" to ex.message))
            .build()
    }
}