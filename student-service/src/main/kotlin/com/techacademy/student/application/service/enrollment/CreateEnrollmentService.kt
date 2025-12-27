package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.CreateEnrollmentDTO
import com.techacademy.student.application.dto.EnrollmentDTO
import com.techacademy.student.application.mapper.enrollment.toDTO
import com.techacademy.student.application.mapper.enrollment.toDomain
import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.application.usecase.enrollment.CreateEnrollmentUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class CreateEnrollmentService(
    private val enrollmentRepository: EnrollmentRepositoryPort,
    private val studentRepository: StudentRepositoryPort,
    private val classroomRepository: ClassroomRepositoryPort
): CreateEnrollmentUseCase {

    @Transactional
    override fun execute(createEnrollment: CreateEnrollmentDTO): EnrollmentDTO {
        studentRepository
            .findStudent(createEnrollment.studentId)
            ?: throw StudentNotExistsException()

        classroomRepository
            .findClassroom(createEnrollment.classroomId)
            ?: throw ClassroomNotExistsException()

        val enrollment = createEnrollment.toDomain()
        val now = LocalDateTime.now()

        enrollment.createdAt = now
        enrollment.updatedAt = now

        val savedEnrollment = enrollmentRepository.createEnrollment(enrollment)

        return savedEnrollment.toDTO()
    }
}