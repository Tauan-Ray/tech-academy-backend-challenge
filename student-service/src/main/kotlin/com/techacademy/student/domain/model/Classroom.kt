package com.techacademy.student.domain.model

import java.time.LocalDateTime

data class Classroom(
    val id: Int? = null,
    val year: Int,
    val course: String,
    val grade: Int,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null,
)
