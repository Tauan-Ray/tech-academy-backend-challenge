package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import com.techacademy.student.application.usecase.enrollment.FindEnrollmentByClassroomUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindEnrollmentByClassroomService(
    private val enrollmentRepository: EnrollmentRepositoryPort,
    private val classroomRepository: ClassroomRepositoryPort,
): FindEnrollmentByClassroomUseCase {

    override fun execute(classroomId: Int): List<EnrollmentDTO> {
        classroomRepository
            .findClassroom(classroomId)
            ?: throw ClassroomNotExistsException()

        return enrollmentRepository
            .findByClassroom(classroomId)
            .map { it.toDTO() }
    }
}