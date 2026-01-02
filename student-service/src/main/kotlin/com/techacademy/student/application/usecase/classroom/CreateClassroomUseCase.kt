package com.techacademy.student.application.usecase.classroom

import com.techacademy.student.application.dto.classroom.ClassroomDTO
import com.techacademy.student.application.dto.classroom.CreateClassroomDTO


interface CreateClassroomUseCase {
    fun execute(createClassroom: CreateClassroomDTO): ClassroomDTO
}