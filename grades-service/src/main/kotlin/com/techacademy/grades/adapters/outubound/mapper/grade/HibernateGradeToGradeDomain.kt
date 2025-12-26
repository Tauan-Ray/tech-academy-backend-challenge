package com.techacademy.grades.adapters.outubound.mapper.grade

import com.techacademy.grades.adapters.outubound.entity.HibernateGradeEntity
import com.techacademy.grades.domain.model.Grade

fun HibernateGradeEntity.toDomain(): Grade =
    Grade(
        id = id,
        studentId = studentId,
        subjectId = subject.id!!,
        score = score,
        bimester = bimester,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )