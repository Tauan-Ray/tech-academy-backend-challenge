package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.ClassroomDTO

interface FindAllClassroomsUseCase {
    fun execute(): List<ClassroomDTO>
}