package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByClassroomUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort

class FindEnrollmentByClassroomService(
    private val enrollmentRepository: EnrollmentRepositoryPort
): FindEnrollmentByClassroomUseCase {

    override fun execute(classroomId: Int): List<EnrollmentDTO> {
        return enrollmentRepository
            .findByClassroom(classroomId)
            .map { it.toDTO() }
    }
}