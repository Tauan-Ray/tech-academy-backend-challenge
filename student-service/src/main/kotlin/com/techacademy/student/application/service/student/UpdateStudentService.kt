package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.student.StudentDTO
import com.techacademy.student.application.dto.student.UpdateStudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.service.exception.MissingDataUpdateStudentException
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.application.usecase.student.UpdateStudentUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class UpdateStudentService(
    private val studentRepository: StudentRepositoryPort
): UpdateStudentUseCase {

    @Transactional
    override fun execute(id: Int, updateStudent: UpdateStudentDTO): StudentDTO {
        if (
            updateStudent.name == null && updateStudent.email == null
        ) throw MissingDataUpdateStudentException()

        val student = studentRepository.findStudent(id)
            ?: throw StudentNotExistsException()

        updateStudent.name?.let { student.name = it }
        updateStudent.email?.let { student.email = it }

        return studentRepository
            .updateStudent(student)
            .toDTO()
    }
}