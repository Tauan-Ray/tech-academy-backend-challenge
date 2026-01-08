package com.techacademy.student.adapters.inbound.documentation.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentWithDetailsDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Matrícula encontrada com sucesso com detalhes do aluno e da sala. Caso não exista, retorna null.",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(implementation = EnrollmentWithDetailsDTO::class)
        )
    ]
)
annotation class FindEnrollmentByIdWithDetailsDoc
