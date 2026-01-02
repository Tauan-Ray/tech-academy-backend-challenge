package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.application.usecase.enrollment.ExistsEnrollmentActiveByStudentUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ExistsEnrollmentActiveByStudentService(
    private val enrollmentRepository: EnrollmentRepositoryPort,
    private val studentRepository: StudentRepositoryPort
): ExistsEnrollmentActiveByStudentUseCase {

    override fun execute(studentId: Int): Boolean {
        studentRepository.findStudent(studentId)
            ?: throw StudentNotExistsException()

        return enrollmentRepository
            .existsActiveByStudent(studentId)
    }
}