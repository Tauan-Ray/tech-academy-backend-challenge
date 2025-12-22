package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.StudentDTO

interface FindStudentUseCase {
    fun execute(id: Int): StudentDTO?
}