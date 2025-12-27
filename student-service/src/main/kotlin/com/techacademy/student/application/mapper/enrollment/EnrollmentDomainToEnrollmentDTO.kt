package com.techacademy.student.application.mapper.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.domain.model.Enrollment

fun Enrollment.toDTO(): EnrollmentDTO =
    EnrollmentDTO(
        id = id,
        studentId = studentId,
        classroomId = classroomId,
        createdAt = createdAt,
    )