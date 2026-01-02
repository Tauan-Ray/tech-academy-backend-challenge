package com.techacademy.student.domain.model

import java.time.LocalDateTime

data class Enrollment (
    val id: Int? = null,
    var studentId: Int,
    var classroomId: Int,
    var active: Boolean = true,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
)