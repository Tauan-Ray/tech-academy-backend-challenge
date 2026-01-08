package com.techacademy.student.adapters.inbound.documentation.enrollment

import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Indica se existe matrícula ativa para o aluno na sala informada",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                type = SchemaType.BOOLEAN,
                example = "true"
            )
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Parâmetros obrigatórios não informados ou inválidos"
)
annotation class ExistsEnrollmentDoc
