package com.techacademy.student.adapters.inbound.documentation.student

import com.techacademy.student.application.dto.student.ReportCardDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Boletim dos alunos gerado com sucesso",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = ReportCardDTO::class,
                type = SchemaType.ARRAY
            )
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Parâmetro studentIds ausente ou em formato inválido"
)
annotation class GenerateReportCardDoc
