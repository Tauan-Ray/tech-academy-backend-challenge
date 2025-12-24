package com.techacademy.grades.application.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateSubjectDTO (
    @field:NotBlank(message = "O nome da disciplina é obrigatório")
    @field:Size(max = 50, message = "O nome da disciplina deve ter no máximo 50 caracteres")
    val name: String,

    @field:Min(value = 1, message = "O ano da disciplina deve estar entre 1º e 3º ano do Ensino Médio")
    @field:Max(value = 3, message = "O ano da disciplina deve estar entre 1º e 3º ano do Ensino Médio")
    val grade: Int,

    @field:Size(max = 50, message = "O nome do curso deve ter no máximo 50 caracteres")
    val course: String? = null,

    @field:Pattern(regexp = "BASE|COURSE_SPECIFIC", message = "Tipo inválido. Valores permitidos: BASE, COURSE_SPECIFIC")
    val type: String,

    @field:Min(value = 1, message = "A carga horária deve ser pelo menos 1 hora")
    val workload: Int,
)
