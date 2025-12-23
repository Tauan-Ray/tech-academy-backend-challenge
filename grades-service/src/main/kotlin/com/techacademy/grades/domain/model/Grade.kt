package com.techacademy.grades.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Grade(
    val id: Int? = null,
    val studentId: Int,
    val subjectId: Int,
    val score: BigDecimal,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
)
