package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.usecase.student.FindStudentByEmailUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort

class FindStudentByEmailService(
    private val studentRepository: StudentRepositoryPort
): FindStudentByEmailUseCase {
    override fun execute(email: String): StudentDTO? {
        return studentRepository.findStudentByEmail(email)
            ?.toDTO()
    }
}