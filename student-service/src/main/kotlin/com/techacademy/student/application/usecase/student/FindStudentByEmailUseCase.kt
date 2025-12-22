package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.StudentDTO

interface FindStudentByEmailUseCase {
    fun execute(email: String): StudentDTO?
}