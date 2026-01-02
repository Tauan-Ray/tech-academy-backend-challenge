package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentWithDetailsDTO
import com.techacademy.student.application.port.EnrollmentQueryPort
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByIdWithDetailsUseCase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindEnrollmentByIdWithDetailsService(
    private val enrollmentQueryPort: EnrollmentQueryPort
): FindEnrollmentByIdWithDetailsUseCase {

    override fun execute(enrollmentId: Int): EnrollmentWithDetailsDTO? {
        return enrollmentQueryPort
            .findByIdWithDetails(enrollmentId)
    }
}