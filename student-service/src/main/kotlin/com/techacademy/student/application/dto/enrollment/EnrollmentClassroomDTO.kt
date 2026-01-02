package com.techacademy.student.application.dto.enrollment

data class EnrollmentClassroomDTO(
    val enrollmentId: Int,
    val active: Boolean,
    val studentId: Int,
    val classroomId: Int,
    val year: Int,
    val course: String,
    val grade: Int,
)
