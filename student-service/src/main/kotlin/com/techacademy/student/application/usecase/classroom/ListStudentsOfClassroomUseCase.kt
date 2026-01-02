package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.student.StudentDTO

interface ListStudentsOfClassroomUseCase {
    fun execute(id: Int): List<StudentDTO>
}