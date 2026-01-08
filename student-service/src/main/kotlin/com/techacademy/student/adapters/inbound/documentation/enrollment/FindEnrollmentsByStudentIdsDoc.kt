package com.techacademy.student.adapters.inbound.documentation.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Lista de matrículas encontradas com base nos IDs dos alunos",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = EnrollmentDTO::class,
                type = SchemaType.ARRAY
            )
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Parâmetro studentIds ausente ou em formato inválido"
)
annotation class FindEnrollmentsByStudentIdsDoc
