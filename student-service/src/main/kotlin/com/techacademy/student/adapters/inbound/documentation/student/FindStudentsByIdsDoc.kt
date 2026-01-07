package com.techacademy.student.adapters.inbound.documentation.student

import com.techacademy.student.application.dto.student.StudentDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Lista de alunos encontrada com sucesso",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = StudentDTO::class,
                type = SchemaType.ARRAY
            )
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Parâmetro studentIds ausente ou em formato inválido"
)
annotation class FindStudentsByIdsDoc
