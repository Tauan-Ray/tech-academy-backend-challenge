package com.techacademy.student.application.dto.student

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UpdateStudentDTO(

    @field:Size(max = 60, message = "O nome deve ter no máximo 60 caracteres")
    val name: String? = null,

    @field:Email(message = "O e-mail informado é inválido")
    @field:Size(
        max = 255,
        message = "O e-mail deve ter no máximo 150 caracteres"
    )
    val email: String? = null
)
