package com.techacademy.grades.adapters.inbound.documentation.subject

import com.techacademy.grades.application.dto.SubjectDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Lista de disciplinas retornada com sucesso",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = SubjectDTO::class,
                type = SchemaType.ARRAY
            )
        )
    ]
)
annotation class FindAllSubjectsDoc
