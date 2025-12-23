package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.CreateStudentDTO
import com.techacademy.student.application.dto.StudentDTO

interface CreateStudentUseCase {
    fun execute(createStudent: CreateStudentDTO): StudentDTO
}