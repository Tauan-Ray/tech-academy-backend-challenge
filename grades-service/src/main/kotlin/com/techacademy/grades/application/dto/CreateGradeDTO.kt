package com.techacademy.grades.application.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal

data class CreateGradeDTO(
    @field:Min(value = 1, message = "O id do estudante é obrigatório")
    val studentId: Int,

    @field:Min(value = 1, message = "O id da disciplina é obrigatório")
    val subjectId: Int,

    @field:Pattern(
        regexp = "BIMESTER_1|BIMESTER_2|BIMESTER_3|BIMESTER_4",
        message = "Tipo inválido. Valores permitidos: BIMESTER_1, BIMESTER_2, BIMESTER_3, BIMESTER_4"
    )
    val bimester: String,

    @field:DecimalMin(value = "0.0", inclusive = true, message = "A nota deve ser no mínimo 0")
    @field:DecimalMax(value = "10.0", inclusive = true, message = "A nota deve ser no máximo 10")
    val score: BigDecimal,
)
