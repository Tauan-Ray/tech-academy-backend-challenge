package com.techacademy.grades.application.dto

import org.eclipse.microprofile.openapi.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal

@Schema(description = "DTO para criação de uma nota")
data class CreateGradeDTO(

    @field:Schema(
        description = "Identificador da matrícula do aluno",
        example = "10"
    )
    @field:Min(value = 1, message = "O id da matrícula é obrigatória")
    val enrollmentId: Int,

    @field:Schema(
        description = "Identificador da disciplina",
        example = "5"
    )
    @field:Min(value = 1, message = "O id da disciplina é obrigatório")
    val subjectId: Int,

    @field:Schema(
        description = "Bimestre da nota",
        example = "BIMESTER_1"
    )
    @field:Pattern(
        regexp = "BIMESTER_1|BIMESTER_2|BIMESTER_3|BIMESTER_4",
        message = "Tipo inválido. Valores permitidos: BIMESTER_1, BIMESTER_2, BIMESTER_3, BIMESTER_4"
    )
    val bimester: String,

    @field:Schema(
        description = "Nota do aluno",
        example = "8.5",
        minimum = "0",
        maximum = "10"
    )
    @field:DecimalMin(value = "0.0", inclusive = true, message = "A nota deve ser no mínimo 0")
    @field:DecimalMax(value = "10.0", inclusive = true, message = "A nota deve ser no máximo 10")
    val score: BigDecimal,
)
