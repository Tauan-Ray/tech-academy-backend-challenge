package com.techacademy.grades.application.mapper.subject

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.domain.model.Subject
import com.techacademy.grades.domain.model.SubjectType

fun CreateSubjectDTO.toDomain(): Subject =
    Subject(
        name = name,
        grade = grade,
        course = course,
        type = SubjectType.valueOf(type),
        workload = workload,
    )