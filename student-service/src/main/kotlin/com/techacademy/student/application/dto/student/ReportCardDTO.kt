package com.techacademy.student.application.dto.student

import java.math.BigDecimal
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "Bimestre escolar")
enum class Bimester {
    BIMESTER_1,
    BIMESTER_2,
    BIMESTER_3,
    BIMESTER_4
}

@Schema(description = "Notas de uma disciplina no boletim")
data class SubjectReportCardDTO(

    @field:Schema(
        description = "Nome da disciplina",
        example = "Matemática"
    )
    val name: String,

    @field:Schema(
        description = "Bimestre da avaliação",
        example = "BIMESTER_1"
    )
    val bimester: Bimester,

    @field:Schema(
        description = "Série",
        example = "2"
    )
    val grade: Int,

    @field:Schema(
        description = "Pontuação obtida",
        example = "8.75"
    )
    val score: BigDecimal
)


@Schema(description = "Boletim escolar do aluno")
data class ReportCardDTO(

    @field:Schema(
        description = "Identificador da matrícula",
        example = "12"
    )
    val enrollmentId: Int,

    @field:Schema(
        description = "Nome do aluno",
        example = "João Silva"
    )
    val name: String,

    @field:Schema(
        description = "Ano letivo",
        example = "2025"
    )
    val year: Int,

    @field:Schema(
        description = "Curso",
        example = "Informática"
    )
    val course: String,

    @field:Schema(
        description = "Série",
        example = "2"
    )
    val grade: Int,

    @field:Schema(
        description = "Indica se a matrícula está ativa",
        example = "true"
    )
    val active: Boolean,

    @field:Schema(
        description = "Lista de disciplinas com notas"
    )
    val subjects: List<SubjectReportCardDTO>
)
