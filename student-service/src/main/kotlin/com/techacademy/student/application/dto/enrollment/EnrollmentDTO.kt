package com.techacademy.student.application.dto.enrollment

import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Representação de uma matrícula")
data class EnrollmentDTO(

    @field:Schema(
        description = "Identificador único da matrícula",
        example = "1"
    )
    val id: Int? = null,

    @field:Schema(
        description = "ID do aluno vinculado à matrícula",
        example = "10",
        required = true
    )
    val studentId: Int,

    @field:Schema(
        description = "ID da sala de aula vinculada à matrícula",
        example = "5",
        required = true
    )
    val classroomId: Int,

    @field:Schema(
        description = "Indica se a matrícula está ativa",
        example = "true",
        required = true
    )
    val active: Boolean,

    @field:Schema(
        description = "Data e hora de criação da matrícula",
        example = "2025-01-01T10:15:30"
    )
    val createdAt: LocalDateTime? = null,
)
