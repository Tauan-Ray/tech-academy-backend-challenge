package com.techacademy.student.adapters.inbound.documentation.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Matrícula criada com sucesso",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(implementation = EnrollmentDTO::class)
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Dados inválidos na requisição"
)
@APIResponse(
    responseCode = "404",
    description = "Aluno ou turma não encontrados"
)
@APIResponse(
    responseCode = "409",
    description = "Aluno já possui matrícula ativa ou já está matriculado nesta turma"
)
annotation class CreateEnrollmentDoc
