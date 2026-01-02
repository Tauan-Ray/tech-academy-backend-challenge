package com.techacademy.grades.adapters.outubound.gateway.dto

import java.time.LocalDateTime

data class EnrollmentResponseDTO (
    val id: Int,
    val studentId: Int,
    val classroomId: Int,
    val active: Boolean,
    val createdAt: LocalDateTime? = null,
)