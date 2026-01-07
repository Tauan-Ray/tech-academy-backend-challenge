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
    description = "Aluno atualizado com sucesso",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(implementation = StudentDTO::class)
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Nenhum dado informado para atualização ou dados inválidos"
)
@APIResponse(
    responseCode = "404",
    description = "Aluno não encontrado"
)
annotation class UpdateStudentDoc
