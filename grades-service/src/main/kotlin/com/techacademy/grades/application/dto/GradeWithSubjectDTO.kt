package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.Bimester
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "Nota do aluno associada à disciplina")
data class GradeWithSubjectDTO(

    @field:Schema(
        description = "Identificador da matrícula",
        example = "15"
    )
    val enrollmentId: Int,

    @field:Schema(
        description = "Identificador da disciplina",
        example = "3"
    )
    val subjectId: Int,

    @field:Schema(
        description = "Nome da disciplina",
        example = "Matemática"
    )
    val subjectName: String,

    @field:Schema(
        description = "Ano/série da disciplina",
        example = "2"
    )
    val grade: Int,

    @field:Schema(
        description = "Bimestre da nota",
        example = "BIMESTER_1"
    )
    val bimester: Bimester,

    @field:Schema(
        description = "Nota obtida",
        example = "8.75"
    )
    val score: BigDecimal
)
