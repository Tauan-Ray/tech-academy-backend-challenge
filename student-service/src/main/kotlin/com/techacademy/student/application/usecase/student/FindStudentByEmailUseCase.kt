package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.student.StudentDTO

interface FindStudentByEmailUseCase {
    fun execute(email: String): StudentDTO?
}