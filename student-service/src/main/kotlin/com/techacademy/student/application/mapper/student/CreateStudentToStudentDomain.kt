package com.techacademy.student.application.mapper.student

import com.techacademy.student.application.dto.student.CreateStudentDTO
import com.techacademy.student.domain.model.Student

fun CreateStudentDTO.toDomain(): Student =
    Student(
        name = name,
        email = email,
    )