package com.techacademy.student.adapters.outbound.repository.classroom

import com.techacademy.student.adapters.outbound.mapper.classroom.toDomain
import com.techacademy.student.domain.model.Classroom
import com.techacademy.student.domain.repository.ClassroomRepositoryPort

class ClassroomRepositoryAdapter(
    private val hibernateClassroomRepository: HibernateClassroomRepository
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

    override fun findClassroomByIdentity(year: Int, course: String, grade: Int): Classroom? {
        return hibernateClassroomRepository
            .find("year = :year AND course = :course AND grade = :course",
                mapOf("year" to year, "course" to course, "grade" to grade))
            .firstResult()
            ?.toDomain()
    }
}