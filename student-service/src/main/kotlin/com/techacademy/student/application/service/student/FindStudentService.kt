package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.usecase.student.FindStudentUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindStudentService(
    private val studentRepository: StudentRepositoryPort
): FindStudentUseCase {
    override fun execute(id: Int): StudentDTO? {
        return studentRepository.findStudent(id)
            ?.toDTO()
    }
}