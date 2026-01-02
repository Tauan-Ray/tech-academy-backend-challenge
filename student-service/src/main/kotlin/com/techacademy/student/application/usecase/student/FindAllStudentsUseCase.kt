package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.student.StudentDTO

interface FindAllStudentsUseCase {
    fun execute(): List<StudentDTO>
}