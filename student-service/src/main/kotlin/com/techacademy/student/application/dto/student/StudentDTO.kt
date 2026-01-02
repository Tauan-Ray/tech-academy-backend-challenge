package com.techacademy.student.application.dto.student

import java.time.LocalDateTime

data class StudentDTO(
    val id: Int? = null,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime? = null,
)
