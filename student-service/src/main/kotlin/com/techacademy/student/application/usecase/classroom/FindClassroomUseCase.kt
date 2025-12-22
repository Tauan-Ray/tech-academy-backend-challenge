package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.ClassroomDTO

interface FindClassroomUseCase {
    fun execute(id: Int): ClassroomDTO?
}