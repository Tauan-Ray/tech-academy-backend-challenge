package com.techacademy.student.application.dto

import java.math.BigDecimal

enum class Bimester {
    BIMESTER_1,
    BIMESTER_2,
    BIMESTER_3,
    BIMESTER_4
}

data class SubjectReportCardDTO(
    val name: String,
    val bimester: Bimester,
    val grade: Int,
    val score: BigDecimal
)

data class ReportCardDTO (
    val enrollmentId: Int,
    val name: String,
    val year: Int,
    val course: String,
    val grade: Int,
    val subjects: List<SubjectReportCardDTO>
)