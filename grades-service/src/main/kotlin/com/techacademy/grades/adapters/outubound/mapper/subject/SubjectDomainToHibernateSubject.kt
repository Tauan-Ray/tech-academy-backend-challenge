package com.techacademy.grades.adapters.outubound.mapper.subject

import com.techacademy.grades.adapters.outubound.entity.HibernateSubjectEntity
import com.techacademy.grades.domain.model.Subject

fun Subject.toEntity(): HibernateSubjectEntity =
    HibernateSubjectEntity().apply {
        id = this@toEntity.id
        name = this@toEntity.name
        grade = this@toEntity.grade
        type = this@toEntity.type
        course = this@toEntity.course
        workload = this@toEntity.workload

        createdAt = requireNotNull(this@toEntity.createdAt)
        updatedAt = requireNotNull(this@toEntity.updatedAt)
        deletedAt = this@toEntity.deletedAt
    }