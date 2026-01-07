package com.techacademy.student.application.dto.student

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "Payload para criação de um aluno")
data class CreateStudentDTO(

    @field:NotBlank(message = "O nome é obrigatório")
    @field:Size(max = 60, message = "O nome deve ter no máximo 60 caracteres")
    @field:Schema(
        description = "Nome completo do aluno",
        example = "João Silva"
    )
    val name: String,

    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "O e-mail informado é inválido")
    @field:Size(max = 255, message = "O e-mail deve ter no máximo 150 caracteres")
    @field:Schema(
        description = "Email do aluno",
        example = "joao.silva@email.com"
    )
    val email: String
)
