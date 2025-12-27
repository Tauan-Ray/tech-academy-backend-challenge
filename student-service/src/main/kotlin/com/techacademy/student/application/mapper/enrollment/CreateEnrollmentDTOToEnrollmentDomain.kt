package com.techacademy.student.application.mapper.enrollment

import com.techacademy.student.application.dto.CreateEnrollmentDTO
import com.techacademy.student.domain.model.Enrollment

fun CreateEnrollmentDTO.toDomain(): Enrollment =
    Enrollment(
        studentId = studentId,
        classroomId = classroomId,
    )
