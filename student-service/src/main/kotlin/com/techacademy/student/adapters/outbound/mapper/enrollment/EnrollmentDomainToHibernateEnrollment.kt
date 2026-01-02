package com.techacademy.student.adapters.outbound.mapper.enrollment

import com.techacademy.student.adapters.outbound.entity.HibernateClassroomEntity
import com.techacademy.student.adapters.outbound.entity.HibernateEnrollmentEntity
import com.techacademy.student.adapters.outbound.entity.HibernateStudentEntity
import com.techacademy.student.domain.model.Enrollment

fun Enrollment.toEntity(
    student: HibernateStudentEntity,
    classroom: HibernateClassroomEntity,
): HibernateEnrollmentEntity =
    HibernateEnrollmentEntity().apply {
        id = this@toEntity.id
        this.student = student
        this.classroom = classroom
        active = this@toEntity.active

        createdAt = requireNotNull(this@toEntity.createdAt)
        updatedAt = requireNotNull(this@toEntity.updatedAt)
        deletedAt = this@toEntity.deletedAt
    }