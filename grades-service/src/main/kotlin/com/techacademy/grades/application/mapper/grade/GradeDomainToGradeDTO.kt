package com.techacademy.grades.application.mapper.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.domain.model.Grade

fun Grade.toDTO(): GradeDTO =
    GradeDTO(
        id = id,
        studentId = studentId,
        subjectId = subjectId,
        score = score,
        bimester = bimester,
        createdAt = createdAt,
    )