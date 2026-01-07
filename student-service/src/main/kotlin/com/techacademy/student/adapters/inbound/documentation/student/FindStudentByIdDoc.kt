package com.techacademy.student.adapters.inbound.documentation.student

import com.techacademy.student.application.dto.student.StudentDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Aluno encontrado ou null quando não existir",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(implementation = StudentDTO::class)
        )
    ]
)
annotation class FindStudentByIdDoc
