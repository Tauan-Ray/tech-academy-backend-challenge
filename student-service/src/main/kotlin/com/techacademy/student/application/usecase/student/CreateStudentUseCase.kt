package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.student.CreateStudentDTO
import com.techacademy.student.application.dto.student.StudentDTO

interface CreateStudentUseCase {
    fun execute(createStudent: CreateStudentDTO): StudentDTO
}