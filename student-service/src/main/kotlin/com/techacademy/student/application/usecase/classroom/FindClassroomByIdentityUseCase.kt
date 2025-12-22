package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.ClassroomDTO

interface FindClassroomByIdentityUseCase {
    fun execute(
        year: Int,
        course: String,
        grade: Int
    ): ClassroomDTO?
}