package com.techacademy.student.adapters.outbound.mapper.enrollment

import com.techacademy.student.adapters.outbound.entity.HibernateEnrollmentEntity
import com.techacademy.student.domain.model.Enrollment

fun Enrollment.toEntity(): HibernateEnrollmentEntity =
    HibernateEnrollmentEntity().apply {
        id = this@toEntity.id
        studentId = this@toEntity.studentId
        classroomId = this@toEntity.classroomId

        createdAt = requireNotNull(this@toEntity.createdAt)
        updatedAt = requireNotNull(this@toEntity.updatedAt)
        deletedAt = this@toEntity.deletedAt
    }