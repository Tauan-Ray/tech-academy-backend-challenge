package com.techacademy.student.adapters.outbound.repository.enrollment

import com.techacademy.student.adapters.outbound.mapper.classroom.toDTO
import com.techacademy.student.adapters.outbound.mapper.enrollment.toEnrollmentClassroomDTO
import com.techacademy.student.adapters.outbound.mapper.student.toDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentClassroomDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentWithDetailsDTO
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

    override fun findByIdWithDetails(enrollmentId: Int): EnrollmentWithDetailsDTO? {
        val enrollment = hibernateEnrollmentRepository
            .find("id IN ?1 AND deletedAt IS NULL", enrollmentId)
            .firstResult()

        if (enrollment == null) return null

        return EnrollmentWithDetailsDTO(
            id = enrollment.id,
            student = enrollment.student.toDTO(),
            classroom = enrollment.classroom.toDTO(),
            active = enrollment.active,
            createdAt = enrollment.createdAt
        )
    }
}