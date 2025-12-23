package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.usecase.classroom.ListStudentsOfClassroomUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ListStudentsOfClassroomService(
    private val classroomRepository: ClassroomRepositoryPort
): ListStudentsOfClassroomUseCase {
    override fun execute(id: Int): List<StudentDTO> {
        return classroomRepository
            .listStudentsOfClassroom(id)
            .map { it.toDTO() }
    }
}