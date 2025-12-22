package com.techacademy.student.application.dto

import java.time.LocalDateTime

data class StudentDTO(
    val id: Int? = null,
    var name: String,
    var email: String,
    var createdAt: LocalDateTime? = null,
)
