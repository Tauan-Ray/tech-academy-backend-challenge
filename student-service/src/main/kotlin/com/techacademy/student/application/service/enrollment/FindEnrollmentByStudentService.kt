package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByStudentUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindEnrollmentByStudentService(
    private val enrollmentRepository: EnrollmentRepositoryPort,
    private val studentRepository: StudentRepositoryPort,
): FindEnrollmentByStudentUseCase {

    override fun execute(studentId: Int): List<EnrollmentDTO> {
        studentRepository
            .findStudent(studentId)
            ?: throw StudentNotExistsException()

        return enrollmentRepository
            .findByStudent(studentId)
            .map { it.toDTO() }
    }
}