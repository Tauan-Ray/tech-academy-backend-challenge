package com.techacademy.student.application.dto

data class CreateStudentDTO(
    val name: String,
    val email: String,
    val classroomId: Int,
)
