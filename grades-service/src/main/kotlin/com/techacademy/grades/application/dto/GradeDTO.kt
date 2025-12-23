package com.techacademy.grades.application.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class GradeDTO(
    val id: Int? = null,
    val studentId: Int,
    val subjectId: Int,
    val score: BigDecimal,
    val createdAt: LocalDateTime? = null,
)