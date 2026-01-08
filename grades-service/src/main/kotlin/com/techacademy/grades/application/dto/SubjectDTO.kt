package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.SubjectType
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Representação de uma disciplina")
data class SubjectDTO(

    @field:Schema(
        description = "Identificador único da disciplina",
        example = "1"
    )
    val id: Int? = null,

    @field:Schema(
        description = "Nome da disciplina",
        example = "Matemática"
    )
    val name: String,

    @field:Schema(
        description = "Ano/série da disciplina",
        example = "1"
    )
    val grade: Int,

    @field:Schema(
        description = "Curso associado à disciplina. Presente apenas para disciplinas do tipo COURSE_SPECIFIC",
        example = "Informática",
        nullable = true
    )
    val course: String? = null,

    @field:Schema(
        description = "Tipo da disciplina",
        example = "BASE",
    )
    val type: SubjectType,

    @field:Schema(
        description = "Carga horária da disciplina em horas",
        example = "80"
    )
    val workload: Int,

    @field:Schema(
        description = "Data e hora de criação da disciplina",
        example = "2025-01-01T10:15:30"
    )
    val createdAt: LocalDateTime? = null,
)
