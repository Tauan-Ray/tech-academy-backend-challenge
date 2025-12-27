package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByIdUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindEnrollmentByIdService(
    private val enrollmentRepository: EnrollmentRepositoryPort
): FindEnrollmentByIdUseCase {

    override fun execute(enrollmentId: Int): EnrollmentDTO? {
        return enrollmentRepository
            .findById(enrollmentId)
            ?.toDTO()
    }
}