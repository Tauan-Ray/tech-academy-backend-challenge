package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.CreateStudentDTO
import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.mapper.student.toDomain
import com.techacademy.student.application.service.exception.EmailAlreadyExistsException
import com.techacademy.student.application.usecase.student.CreateStudentUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class CreateStudentService(
    private val studentRepository: StudentRepositoryPort
): CreateStudentUseCase {

    @Transactional
    override fun execute(createStudent: CreateStudentDTO): StudentDTO {
        val existingEmail = studentRepository.findStudentByEmail(createStudent.email)

        if (existingEmail != null) throw EmailAlreadyExistsException()

        val student = createStudent.toDomain()
        val now = LocalDateTime.now()

        student.createdAt = now
        student.updatedAt = now

        val savedStudent = studentRepository.createStudent(student)

        return savedStudent.toDTO()
    }
}