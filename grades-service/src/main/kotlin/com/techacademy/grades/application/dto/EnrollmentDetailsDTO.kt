package com.techacademy.grades.application.dto

import java.time.LocalDateTime

data class EnrollmentDetailsDTO (
    val id: Int? = null,
    val classroomId: Int,
    val year: Int,
    val course: String,
    val grade: Int,
    val active: Boolean,
    val createdAt: LocalDateTime? = null,
)