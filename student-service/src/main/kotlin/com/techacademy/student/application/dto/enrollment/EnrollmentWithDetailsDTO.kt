package com.techacademy.student.application.dto.enrollment

import com.techacademy.student.application.dto.classroom.ClassroomDTO
import com.techacademy.student.application.dto.student.StudentDTO
import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Representação de uma matrícula com detalhes do aluno e da sala de aula")
data class EnrollmentWithDetailsDTO(

    @field:Schema(
        description = "Identificador único da matrícula",
        example = "10"
    )
    val id: Int? = null,

    @field:Schema(
        description = "Dados do aluno associado à matrícula"
    )
    val student: StudentDTO,

    @field:Schema(
        description = "Dados da sala de aula associada à matrícula"
    )
    val classroom: ClassroomDTO,

    @field:Schema(
        description = "Indica se a matrícula está ativa",
        example = "true"
    )
    val active: Boolean,

    @field:Schema(
        description = "Data e hora de criação da matrícula",
        example = "2025-01-01T10:15:30"
    )
    val createdAt: LocalDateTime? = null,
)
