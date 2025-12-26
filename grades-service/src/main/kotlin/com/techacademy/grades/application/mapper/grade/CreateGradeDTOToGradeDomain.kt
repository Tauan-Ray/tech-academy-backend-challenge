package com.techacademy.grades.application.mapper.grade

import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.domain.model.Grade

fun CreateGradeDTO.toDomain(): Grade =
    Grade(
        studentId = studentId,
        subjectId = subjectId,
        score = score,
    )