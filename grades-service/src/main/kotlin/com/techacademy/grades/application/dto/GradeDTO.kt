package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.Bimester
import java.math.BigDecimal
import java.time.LocalDateTime

data class GradeDTO(
    val id: Int? = null,
    val enrollmentId: Int,
    val subjectId: Int,
    val score: BigDecimal,
    val bimester: Bimester,
    val createdAt: LocalDateTime? = null,
)