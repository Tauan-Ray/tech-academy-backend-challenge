package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.SubjectType
import java.time.LocalDateTime

data class SubjectDTO(
    val id: Int? = null,
    val name: String,
    val grade: Int,
    val course: String? = null,
    val type: SubjectType,
    val workload: Int,
    val createdAt: LocalDateTime? = null,
)