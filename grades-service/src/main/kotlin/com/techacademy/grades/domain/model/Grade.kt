package com.techacademy.grades.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

enum class Bimester {
    BIMESTER_1,
    BIMESTER_2,
    BIMESTER_3,
    BIMESTER_4
}

data class Grade(
    val id: Int? = null,
    val studentId: Int,
    val subjectId: Int,
    val bimester: Bimester,
    val score: BigDecimal,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
)
