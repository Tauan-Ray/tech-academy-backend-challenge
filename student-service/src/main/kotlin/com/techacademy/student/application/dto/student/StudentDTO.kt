package com.techacademy.student.application.dto.student

import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Representação de um aluno")
data class StudentDTO(

    @field:Schema(
        description = "Identificador único do aluno",
        example = "1"
    )
    val id: Int? = null,

    @field:Schema(
        description = "Nome completo do aluno",
        example = "João Silva"
    )
    val name: String,

    @field:Schema(
        description = "Email do aluno",
        example = "joao.silva@email.com"
    )
    val email: String,

    @field:Schema(
        description = "Data e hora de criação do aluno",
        example = "2025-01-01T10:15:30"
    )
    val createdAt: LocalDateTime? = null,
)

