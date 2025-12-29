package com.techacademy.student.application.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CreateEnrollmentDTO(

    @field:NotNull(message = "O id do estudante é obrigatório")
    @field:Positive(message = "O id do estudante deve ser um número positivo")
    val studentId: Int,

    @field:NotNull(message = "O id da turma é obrigatório")
    @field:Positive(message = "O id da turma deve ser um número positivo")
    val classroomId: Int
)
