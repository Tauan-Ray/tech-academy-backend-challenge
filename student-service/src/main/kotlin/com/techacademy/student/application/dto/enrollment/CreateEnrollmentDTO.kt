package com.techacademy.student.application.dto.enrollment

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "Dados necessários para criar uma matrícula")
data class CreateEnrollmentDTO(

    @field:NotNull(message = "O id do estudante é obrigatório")
    @field:Positive(message = "O id do estudante deve ser um número positivo")
    @field:Schema(
        description = "ID do aluno que será matriculado",
        example = "10",
        required = true
    )
    val studentId: Int,

    @field:NotNull(message = "O id da turma é obrigatório")
    @field:Positive(message = "O id da turma deve ser um número positivo")
    @field:Schema(
        description = "ID da turma onde o aluno será matriculado",
        example = "3",
        required = true
    )
    val classroomId: Int
)
