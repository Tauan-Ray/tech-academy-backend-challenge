package com.techacademy.student.domain.model

import java.time.LocalDateTime

data class Classroom(
    val id: Int? = null,
    var year: Int,
    var course: String,
    var grade: Int,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
)
