package com.techacademy.student.adapters.outbound.mapper.classroom

import com.techacademy.student.adapters.outbound.entity.HibernateClassroomEntity
import com.techacademy.student.domain.model.Classroom

fun Classroom.toEntity(): HibernateClassroomEntity =
    HibernateClassroomEntity().apply {
        id = this@toEntity.id
        year = this@toEntity.year
        course = this@toEntity.course
        grade = this@toEntity.grade

        createdAt = requireNotNull(this@toEntity.createdAt)
        updatedAt = requireNotNull(this@toEntity.updatedAt)
        deletedAt = this@toEntity.deletedAt
    }