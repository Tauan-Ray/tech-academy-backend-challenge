package com.techacademy.grades.adapters.outubound.gateway.dto

import java.time.LocalDateTime

data class ClassroomResponseDTO(
    val id: Int? = null,
    val year: Int,
    val course: String,
    val grade: Int,
    val createdAt: LocalDateTime? = null,
)
