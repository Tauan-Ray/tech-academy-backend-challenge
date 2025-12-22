package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.usecase.student.FindAllStudentsUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindAllStudentsService(
    private val studentRepository: StudentRepositoryPort
): FindAllStudentsUseCase {
    override fun execute(): List<StudentDTO> {
        return studentRepository
            .findAll()
            .map { it.toDTO() }
    }
}