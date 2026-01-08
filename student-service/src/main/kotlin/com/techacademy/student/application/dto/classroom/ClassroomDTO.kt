package com.techacademy.student.application.dto.classroom

import java.time.LocalDateTime
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "Representa uma sala de aula")
data class ClassroomDTO(
    @field:Schema(description = "ID da sala de aula", example = "1")
    val id: Int? = null,

    @field:Schema(description = "Ano da turma", example = "2026")
    val year: Int,

    @field:Schema(description = "Curso da turma", example = "Informática")
    val course: String,

    @field:Schema(description = "Série da turma", example = "3")
    val grade: Int,

    @field:Schema(description = "Data de criação da sala de aula")
    val createdAt: LocalDateTime? = null,
)
