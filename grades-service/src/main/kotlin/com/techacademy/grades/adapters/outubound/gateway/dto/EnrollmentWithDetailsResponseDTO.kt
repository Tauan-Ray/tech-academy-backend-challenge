package com.techacademy.grades.adapters.outubound.gateway.dto

import java.time.LocalDateTime

data class EnrollmentWithDetailsResponseDTO(
    val id: Int? = null,
    val student: StudentResponseDTO,
    val classroom: ClassroomResponseDTO,
    val createdAt: LocalDateTime? = null,
)
