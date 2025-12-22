package com.techacademy.student.application.dto

import java.time.LocalDateTime

data class StudentDTO(
    val id: Int? = null,
    val name: String,
    val email: String,
    val classroomId: Int,
    val createdAt: LocalDateTime? = null,
)
