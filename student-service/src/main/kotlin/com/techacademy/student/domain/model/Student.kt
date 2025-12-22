package com.techacademy.student.domain.model

import java.time.LocalDateTime

data class Student(
    val id: Int? = null,
    var name: String,
    var email: String,
    var classroomId: Int,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
)