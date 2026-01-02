package com.techacademy.student.application.dto.classroom

import java.time.LocalDateTime

data class ClassroomDTO(
    val id: Int? = null,
    val year: Int,
    val course: String,
    val grade: Int,
    val createdAt: LocalDateTime? = null,
)