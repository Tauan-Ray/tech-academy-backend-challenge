package com.techacademy.student.application.dto

data class EnrollmentClassroomDTO(
    val enrollmentId: Int,
    val studentId: Int,
    val classroomId: Int,
    val year: Int,
    val course: String,
    val grade: Int,
)
