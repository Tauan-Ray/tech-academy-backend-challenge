package com.techacademy.grades.adapters.inbound.documentation.grade

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses

@APIResponses(
    value = [
        APIResponse(
            responseCode = "200",
            description = "Notas encontradas com sucesso"
        ),
        APIResponse(
            responseCode = "400",
            description = "Parâmetro studentIds ausente ou inválido"
        ),
        APIResponse(
            responseCode = "404",
            description = "Nenhum aluno encontrado"
        )
    ]
)
annotation class FindGradesByStudentsDoc
