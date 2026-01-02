package com.techacademy.student.application.dto.enrollment

import com.techacademy.student.application.dto.classroom.ClassroomDTO
import com.techacademy.student.application.dto.student.StudentDTO
import java.time.LocalDateTime

data class EnrollmentWithDetailsDTO(
    val id: Int? = null,
    val student: StudentDTO,
    val classroom: ClassroomDTO,
    val createdAt: LocalDateTime? = null,
)
