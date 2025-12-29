package com.techacademy.grades.application.mapper.grade

import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.model.Grade

fun CreateGradeDTO.toDomain(): Grade =
    Grade(
        enrollmentId = enrollmentId,
        subjectId = subjectId,
        bimester = Bimester.valueOf(bimester),
        score = score,
    )