package com.techacademy.student.adapters.outbound.repository.enrollment

import com.techacademy.student.adapters.outbound.mapper.enrollment.toDomain
import com.techacademy.student.adapters.outbound.mapper.enrollment.toEntity
import com.techacademy.student.domain.model.Enrollment
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EnrollmentRepositoryAdapter(
    private val hibernateEnrollmentRepository: HibernateEnrollmentRepository
): EnrollmentRepositoryPort {
    override fun findAll(): List<Enrollment> {
        return hibernateEnrollmentRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findById(enrollmentId: Int): Enrollment? {
        return hibernateEnrollmentRepository
            .findById(enrollmentId.toLong())
            ?.toDomain()
    }

    override fun findByStudent(studentId: Int): List<Enrollment> {
        return hibernateEnrollmentRepository
            .find("student.id = ?1 AND deletedAt IS NULL", studentId)
            .list()
            .map { it.toDomain() }
    }

    override fun findByClassroom(classroomId: Int): List<Enrollment> {
        return hibernateEnrollmentRepository
            .find("classroom.id = ?1 AND deletedAt IS NULL", classroomId)
            .list()
            .map { it.toDomain() }
    }

    override fun existsByStudentAndClassroom(studentId: Int, classroomId: Int): Boolean {
        return hibernateEnrollmentRepository
            .find("classroom.id = ?1 AND student.id = ?2", classroomId, studentId)
            .count() > 0
    }

    override fun createEnrollment(enrollment: Enrollment): Enrollment {
        val entity = enrollment.toEntity()
        hibernateEnrollmentRepository.persist(entity)

        return entity.toDomain()
    }
}