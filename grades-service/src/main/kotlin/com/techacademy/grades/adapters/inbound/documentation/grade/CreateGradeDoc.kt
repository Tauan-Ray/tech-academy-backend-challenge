package com.techacademy.grades.adapters.inbound.documentation.grade

import com.techacademy.grades.application.dto.GradeDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponses(
    value = [
        APIResponse(
            responseCode = "201",
            description = "Nota criada com sucesso",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = Schema(implementation = GradeDTO::class)
                )
            ]
        ),
        APIResponse(
            responseCode = "400",
            description = "Dados inválidos ou combinação inválida entre matrícula e disciplina"
        ),
        APIResponse(
            responseCode = "404",
            description = "Matrícula ou disciplina não encontrada"
        ),
        APIResponse(
            responseCode = "409",
            description = "Já existe uma nota cadastrada para este bimestre"
        )
    ]
)
annotation class CreateGradeDoc
