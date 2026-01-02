package com.techacademy.student.adapters.outbound.mapper.enrollment

import com.techacademy.student.adapters.outbound.entity.HibernateEnrollmentEntity
import com.techacademy.student.application.dto.enrollment.EnrollmentClassroomDTO

fun HibernateEnrollmentEntity.toEnrollmentClassroomDTO(): EnrollmentClassroomDTO =
    EnrollmentClassroomDTO(
        enrollmentId = id!!,
        studentId = student.id!!,
        classroomId = classroom.id!!,
        year = classroom.year,
        course = classroom.course,
        grade = classroom.grade,
    )