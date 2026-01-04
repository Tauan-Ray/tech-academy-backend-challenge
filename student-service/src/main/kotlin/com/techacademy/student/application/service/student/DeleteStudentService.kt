package com.techacademy.student.application.service.student

import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.application.usecase.student.DeleteStudentUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class DeleteStudentService(
    private val studentRepository: StudentRepositoryPort
): DeleteStudentUseCase {

    @Transactional
    override fun execute(id: Int) {
        studentRepository.findStudent(id)
            ?: throw StudentNotExistsException()

        studentRepository.deleteStudent(id)
    }
}