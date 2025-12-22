package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.mapper.classroom.toDTO
import com.techacademy.student.application.usecase.classroom.FindClassroomUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindClassroomService(
    private val classroomRepository: ClassroomRepositoryPort
): FindClassroomUseCase {
    override fun execute(id: Int): ClassroomDTO? {
        return classroomRepository
            .findClassroom(id)
            ?.toDTO()
    }
}