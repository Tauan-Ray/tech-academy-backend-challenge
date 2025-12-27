package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.usecase.enrollment.ExistsByStudentAndClassroomUseCase
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ExistsByStudentAndClassroomService(
    private val enrollmentRepository: EnrollmentRepositoryPort
): ExistsByStudentAndClassroomUseCase {

    override fun execute(studentId: Int, classroomId: Int): Boolean {
        return enrollmentRepository
            .existsByStudentAndClassroom(studentId, classroomId)
    }
}