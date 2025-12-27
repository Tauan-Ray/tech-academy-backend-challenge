package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.usecase.enrollment.FindAllEnrollmentsUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort

class FindAllEnrollmentService(
    private val enrollmentRepository: EnrollmentRepositoryPort
): FindAllEnrollmentsUseCase {

    override fun execute(): List<EnrollmentDTO> {
        return enrollmentRepository
            .findAll()
            .map { it.toDTO() }
    }
}