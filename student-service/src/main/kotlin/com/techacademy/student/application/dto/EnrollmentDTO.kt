package com.techacademy.student.application.dto

import java.time.LocalDateTime

data class EnrollmentDTO (
    val id: Int? = null,
    val studentId: Int,
    val classroomId: Int,
    val createdAt: LocalDateTime? = null,
)