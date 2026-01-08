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
    responseCode = "201",
    description = "Disciplina criada com sucesso",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = SubjectDTO::class,
                type = SchemaType.OBJECT
            )
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Dados inválidos ou combinação inválida de tipo e curso"
)
@APIResponse(
    responseCode = "404",
    description = "Turma não encontrada para o curso e série informados"
)
@APIResponse(
    responseCode = "409",
    description = "Disciplina já cadastrada"
)
annotation class CreateSubjectDoc
