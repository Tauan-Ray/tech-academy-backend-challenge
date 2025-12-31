package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.Bimester
import java.math.BigDecimal

data class GradeWithSubjectDTO(
    val enrollmentId: Int,
    val subjectId: Int,
    val subjectName: String,
    val grade: Int,
    val bimester: Bimester,
    val score: BigDecimal
)
