package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.classroom.ClassroomDTO

interface FindClassroomByIdentityUseCase {
    fun execute(
        year: Int?,
        course: String?,
        grade: Int?
    ): List<ClassroomDTO>
}