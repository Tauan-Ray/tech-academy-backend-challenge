package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.dto.CreateClassroomDTO


interface CreateClassroomUseCase {
    fun execute(createClassroom: CreateClassroomDTO): ClassroomDTO
}