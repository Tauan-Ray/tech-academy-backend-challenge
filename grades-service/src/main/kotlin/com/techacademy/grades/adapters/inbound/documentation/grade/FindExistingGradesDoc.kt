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
            description = "Nenhum filtro informado"
        )
    ]
)
annotation class FindExistingGradesDoc
