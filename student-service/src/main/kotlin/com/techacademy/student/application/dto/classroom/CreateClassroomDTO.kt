package com.techacademy.student.application.dto.classroom

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "DTO para criar uma nova sala de aula")
data class CreateClassroomDTO(

    @field:NotNull(message = "O ano é obrigatório")
    @field:Schema(
        description = "Ano da sala de aula",
        example = "2026",
        required = true
    )
    val year: Int,

    @field:NotBlank(message = "O curso é obrigatório")
    @field:Size(max = 100, message = "O curso deve ter no máximo 100 caracteres")
    @field:Schema(
        description = "Nome do curso da sala de aula",
        example = "Informática",
        required = true
    )
    val course: String,

    @field:NotNull(message = "A série é obrigatória")
    @field:Min(value = 1, message = "O ano da turma deve estar entre 1º e 3º ano do Ensino Médio")
    @field:Max(value = 3, message = "O ano da turma deve estar entre 1º e 3º ano do Ensino Médio")
    @field:Schema(
        description = "Série da sala de aula",
        example = "2",
        required = true
    )
    val grade: Int
)
