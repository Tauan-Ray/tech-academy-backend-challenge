package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.Bimester
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime

@Schema(description = "Representação de uma nota")
data class GradeDTO(

    @field:Schema(
        description = "Identificador único da nota",
        example = "1"
    )
    val id: Int? = null,

    @field:Schema(
        description = "Identificador da matrícula relacionada à nota",
        example = "10"
    )
    val enrollmentId: Int,

    @field:Schema(
        description = "Identificador da disciplina relacionada à nota",
        example = "5"
    )
    val subjectId: Int,

    @field:Schema(
        description = "Pontuação obtida pelo aluno",
        example = "8.75"
    )
    val score: BigDecimal,

    @field:Schema(
        description = "Bimestre da nota",
        example = "BIMESTER_1"
    )
    val bimester: Bimester,

    @field:Schema(
        description = "Data de criação da nota",
        example = "2025-01-01T10:15:30"
    )
    val createdAt: LocalDateTime? = null,
)