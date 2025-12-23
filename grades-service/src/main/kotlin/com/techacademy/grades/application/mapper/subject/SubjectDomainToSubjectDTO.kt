package com.techacademy.grades.application.mapper.subject

import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.domain.model.Subject

fun Subject.toDTO(): SubjectDTO =
    SubjectDTO(
        id = id,
        name = name,
        grade = grade,
        course = course,
        type = type,
        workload = workload,
        createdAt = createdAt,
    )