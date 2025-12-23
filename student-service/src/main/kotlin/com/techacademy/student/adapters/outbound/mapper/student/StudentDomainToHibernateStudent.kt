package com.techacademy.student.adapters.outbound.mapper.student

import com.techacademy.student.adapters.outbound.entity.HibernateClassroomEntity
import com.techacademy.student.adapters.outbound.entity.HibernateStudentEntity
import com.techacademy.student.domain.model.Student

fun Student.toEntity(): HibernateStudentEntity =
    HibernateStudentEntity().apply {
        id = this@toEntity.id
        name = this@toEntity.name
        email = this@toEntity.email

        classroom = HibernateClassroomEntity().apply {
            id = this@toEntity.classroomId
        }

        createdAt = requireNotNull(this@toEntity.createdAt)
        updatedAt = requireNotNull(this@toEntity.updatedAt)
        deletedAt = this@toEntity.deletedAt
    }