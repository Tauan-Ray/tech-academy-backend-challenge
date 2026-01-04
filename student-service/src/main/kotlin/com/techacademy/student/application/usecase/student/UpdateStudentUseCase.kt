package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.student.StudentDTO
import com.techacademy.student.application.dto.student.UpdateStudentDTO

interface UpdateStudentUseCase {
    fun execute(id: Int, updateStudent: UpdateStudentDTO): StudentDTO
}