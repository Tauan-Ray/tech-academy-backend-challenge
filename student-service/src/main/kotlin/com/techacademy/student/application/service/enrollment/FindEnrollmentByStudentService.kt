package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByStudentUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindEnrollmentByStudentService(
    private val enrollmentRepository: EnrollmentRepositoryPort
): FindEnrollmentByStudentUseCase {

    override fun execute(studentId: Int): List<EnrollmentDTO> {
        return enrollmentRepository
            .findByStudent(studentId)
            .map { it.toDTO() }
    }
}