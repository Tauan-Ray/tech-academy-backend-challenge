package com.techacademy.student.adapters.outbound.repository.enrollment

import com.techacademy.student.adapters.outbound.mapper.enrollment.toEnrollmentClassroomDTO
import com.techacademy.student.application.dto.EnrollmentClassroomDTO
import com.techacademy.student.application.port.EnrollmentQueryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateEnrollmentQueryAdapter(
    private val hibernateEnrollmentRepository: HibernateEnrollmentRepository
): EnrollmentQueryPort {

    override fun findEnrollmentWithClassroomByStudentIds(studentIds: List<Int>): List<EnrollmentClassroomDTO> {
        return hibernateEnrollmentRepository
            .find("student.id IN ?1 AND deletedAt IS NULL", studentIds)
            .list()
            .map { it.toEnrollmentClassroomDTO() }
    }
}