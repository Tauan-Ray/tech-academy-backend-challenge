package com.techacademy.student.adapters.outbound.mapper.enrollment

import com.techacademy.student.adapters.outbound.entity.HibernateEnrollmentEntity
import com.techacademy.student.domain.model.Enrollment

fun HibernateEnrollmentEntity.toDomain(): Enrollment =
    Enrollment(
        id = id,
        studentId = student.id!!,
        classroomId = classroom.id!!,
        active = active,

        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
    )