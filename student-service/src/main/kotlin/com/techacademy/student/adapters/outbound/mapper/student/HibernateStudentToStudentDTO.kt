package com.techacademy.student.adapters.outbound.mapper.student

import com.techacademy.student.adapters.outbound.entity.HibernateStudentEntity
import com.techacademy.student.application.dto.student.StudentDTO

fun HibernateStudentEntity.toDTO(): StudentDTO =
    StudentDTO(
        id = id,
        name = name,
        email = email,
        createdAt = createdAt
    )