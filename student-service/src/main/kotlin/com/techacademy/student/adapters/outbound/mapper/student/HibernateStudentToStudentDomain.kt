package com.techacademy.student.adapters.outbound.mapper.student

import com.techacademy.student.adapters.outbound.entity.HibernateStudentEntity
import com.techacademy.student.domain.model.Student

fun HibernateStudentEntity.toDomain(): Student =
    Student(
        id = id,
        name = name,
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )