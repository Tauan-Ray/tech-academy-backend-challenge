package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.mapper.classroom.toDTO
import com.techacademy.student.application.usecase.classroom.FindAllClassroomsUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort

class FindAllClassroomsService(
    private val classroomRepository: ClassroomRepositoryPort
): FindAllClassroomsUseCase {
    override fun execute(): List<ClassroomDTO> {
        return classroomRepository
            .findAll()
            .map { it.toDTO() }
    }
}