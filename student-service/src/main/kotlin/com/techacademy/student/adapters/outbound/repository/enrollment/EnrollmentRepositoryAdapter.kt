package com.techacademy.student.adapters.outbound.repository.enrollment

import com.techacademy.student.adapters.outbound.mapper.enrollment.toDomain
import com.techacademy.student.adapters.outbound.mapper.enrollment.toEntity
import com.techacademy.student.adapters.outbound.repository.classroom.HibernateClassroomRepository
import com.techacademy.student.adapters.outbound.repository.student.HibernateStudentRepository
import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.model.Enrollment
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EnrollmentRepositoryAdapter(
    private val hibernateEnrollmentRepository: HibernateEnrollmentRepository,
    private val hibernateClassroomRepository: HibernateClassroomRepository,
    private val hibernateStudentRepository: HibernateStudentRepository
): EnrollmentRepositoryPort {
    override fun findAll(): List<Enrollment> {
        return hibernateEnrollmentRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findAllByStudentIds(studentIds: List<Int>): List<Enrollment> {
        return hibernateEnrollmentRepository
            .find("student.id IN ?1 AND deletedAt IS NULL", studentIds)
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
        val classroom = hibernateClassroomRepository
            .findById(enrollment.classroomId.toLong())
            ?: throw ClassroomNotExistsException()

        val student = hibernateStudentRepository
            .findById(enrollment.studentId.toLong())
            ?: throw StudentNotExistsException()

        val entity = enrollment.toEntity(student, classroom)
        hibernateEnrollmentRepository.persist(entity)

        return entity.toDomain()
    }
}