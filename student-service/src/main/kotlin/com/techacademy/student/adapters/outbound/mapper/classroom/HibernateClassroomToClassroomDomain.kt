package com.techacademy.student.adapters.outbound.mapper.classroom

import com.techacademy.student.adapters.outbound.entity.HibernateClassroomEntity
import com.techacademy.student.domain.model.Classroom

fun HibernateClassroomEntity.toDomain(): Classroom =
    Classroom(
        id = id,
        year = year,
        course = course,
        grade = grade,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt
    )