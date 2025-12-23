package com.techacademy.grades.domain.model

import java.time.LocalDateTime

enum class SubjectType {
    BASE,
    COURSE_SPECIFIC
}

data class Subject(
    val id: Int? = null,
    val name: String,
    val grade: Int,
    val type: SubjectType,
    val course: String? = null,
    val workload: Int,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
)
