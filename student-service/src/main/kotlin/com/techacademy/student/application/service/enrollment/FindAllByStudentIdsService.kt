package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.usecase.enrollment.FindAllByStudentIdsUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindAllByStudentIdsService(
    private val enrollmentRepository: EnrollmentRepositoryPort
): FindAllByStudentIdsUseCase {

    override fun execute(studentIds: List<Int>): List<EnrollmentDTO> {
        return enrollmentRepository
            .findAllByStudentIds(studentIds)
            .map { it.toDTO() }
    }
}