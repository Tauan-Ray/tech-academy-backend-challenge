package com.techacademy.student.application.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateStudentDTO(

    @field:NotBlank(message = "O nome é obrigatório")
    @field:Size(max = 60, message = "O nome deve ter no máximo 60 caracteres")
    val name: String,

    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "O e-mail informado é inválido")
    @field:Size(
        max = 255,
        message = "O e-mail deve ter no máximo 150 caracteres"
    )
    val email: String
)
