package com.techacademy.student.adapters.outbound.repository.classroom

import com.techacademy.student.adapters.outbound.mapper.classroom.toDomain
import com.techacademy.student.adapters.outbound.mapper.classroom.toEntity
import com.techacademy.student.adapters.outbound.mapper.student.toDomain
import com.techacademy.student.adapters.outbound.repository.enrollment.HibernateEnrollmentRepository
import com.techacademy.student.domain.model.Classroom
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassroomRepositoryAdapter(
    private val hibernateClassroomRepository: HibernateClassroomRepository,
    private val hibernateEnrollmentRepository: HibernateEnrollmentRepository
): ClassroomRepositoryPort {
    override fun findAll(): List<Classroom> {
        return hibernateClassroomRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findClassroom(id: Int): Classroom? {
        return hibernateClassroomRepository
            .findById(id.toLong())
            ?.toDomain()
    }

    override fun findClassroomByIdentity(year: Int?, course: String?, grade: Int?): List<Classroom> {
        return hibernateClassroomRepository
            .find(
                """
                    (:year IS NULL OR year = :year)
                    AND (:course IS NULL OR course = :course)
                    AND (:grade IS NULL OR grade = :grade)
                """.trimIndent(),
                mapOf(
                    "year" to year,
                    "course" to course,
                    "grade" to grade
                )
            )
            .list()
            .map { it.toDomain() }
    }


    override fun listStudentsOfClassroom(id: Int): List<Student> {
        val enrollments = hibernateEnrollmentRepository
            .find("classroom.id = ?1 AND deletedAt IS NULL", id)
            .list()
            .map { it.student }

        return enrollments.map { it.toDomain() }
    }

    override fun createClassroom(classroom: Classroom): Classroom {
        val classroom = classroom.toEntity()
        hibernateClassroomRepository.persist(classroom)

        return classroom.toDomain()
    }
}