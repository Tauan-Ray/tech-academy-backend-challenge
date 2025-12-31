package com.techacademy.student.application.dto

import java.math.BigDecimal

data class GradeResponseDTO(
    val enrollmentId: Int,
    val subjectId: Int,
    val subjectName: String,
    val grade: Int,
    val bimester: Bimester,
    val score: BigDecimal
)
