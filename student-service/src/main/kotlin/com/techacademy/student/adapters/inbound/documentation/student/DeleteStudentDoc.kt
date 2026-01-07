package com.techacademy.student.adapters.inbound.documentation.student

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "204",
    description = "Aluno removido com sucesso"
)
@APIResponse(
    responseCode = "404",
    description = "Aluno não encontrado"
)
annotation class DeleteStudentDoc
