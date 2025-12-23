package com.techacademy.grades.adapters.outubound.mapper.subject

import com.techacademy.grades.adapters.outubound.entity.HibernateSubjectEntity
import com.techacademy.grades.domain.model.Subject

fun HibernateSubjectEntity.toDomain(): Subject =
    Subject(
        id = id,
        name = name,
        grade = grade,
        type = type,
        course = course,
        workload = workload,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )