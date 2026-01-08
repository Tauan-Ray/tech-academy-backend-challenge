package com.techacademy.grades.application.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "Dados necessários para criação de uma disciplina")
data class CreateSubjectDTO (

    @field:NotBlank(message = "O nome da disciplina é obrigatório")
    @field:Size(max = 50, message = "O nome da disciplina deve ter no máximo 50 caracteres")
    @field:Schema(
        description = "Nome da disciplina",
        example = "Matemática"
    )
    val name: String,

    @field:Min(value = 1, message = "O ano da disciplina deve estar entre 1º e 3º ano do Ensino Médio")
    @field:Max(value = 3, message = "O ano da disciplina deve estar entre 1º e 3º ano do Ensino Médio")
    @field:Schema(
        description = "Ano/série da disciplina",
        example = "1"
    )
    val grade: Int,

    @field:Size(max = 50, message = "O nome do curso deve ter no máximo 50 caracteres")
    @field:Schema(
        description = "Curso associado à disciplina (obrigatório apenas para disciplinas do tipo COURSE_SPECIFIC)",
        example = "Informática",
        nullable = true
    )
    val course: String? = null,

    @field:Pattern(
        regexp = "BASE|COURSE_SPECIFIC",
        message = "Tipo inválido. Valores permitidos: BASE, COURSE_SPECIFIC"
    )
    @field:Schema(
        description = "Tipo da disciplina",
        example = "BASE",
        enumeration = ["BASE", "COURSE_SPECIFIC"],
    )
    val type: String,

    @field:Min(value = 1, message = "A carga horária deve ser pelo menos 1 hora")
    @field:Schema(
        description = "Carga horária da disciplina em horas",
        example = "80"
    )
    val workload: Int,
)

