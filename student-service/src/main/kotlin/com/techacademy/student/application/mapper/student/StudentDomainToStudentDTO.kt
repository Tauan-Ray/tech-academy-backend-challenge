package com.techacademy.student.application.mapper.student

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.domain.model.Student

fun Student.toDTO(): StudentDTO =
    StudentDTO(
        id = id,
        name = name,
        email = email,
        createdAt = createdAt
    )