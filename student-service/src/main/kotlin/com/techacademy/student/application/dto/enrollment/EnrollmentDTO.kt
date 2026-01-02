package com.techacademy.student.application.dto.enrollment

import java.time.LocalDateTime

data class EnrollmentDTO (
    val id: Int? = null,
    val studentId: Int,
    val classroomId: Int,
    val createdAt: LocalDateTime? = null,
)