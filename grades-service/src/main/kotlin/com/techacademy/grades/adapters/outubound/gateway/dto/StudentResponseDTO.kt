package com.techacademy.grades.adapters.outubound.gateway.dto

import java.time.LocalDateTime

data class StudentResponseDTO(
    val id: Int,
    val name: String,
    val email: String,
    val classroomId: Int,
    val createdAt: LocalDateTime
)