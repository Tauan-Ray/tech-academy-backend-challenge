package com.techacademy.student.adapters.outbound.repository.student

import com.techacademy.student.adapters.outbound.mapper.student.toDomain
import com.techacademy.student.adapters.outbound.mapper.student.toEntity
import com.techacademy.student.adapters.outbound.repository.classroom.HibernateClassroomRepository
import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentRepositoryAdapter(
    private val hibernateStudentRepository: HibernateStudentRepository,
    private val hibernateClassroomRepository: HibernateClassroomRepository
): StudentRepositoryPort {
    override fun findAll(): List<Student> {
        return hibernateStudentRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findStudent(id: Int): Student? {
        return  hibernateStudentRepository
            .findById(id.toLong())
            ?.toDomain()
    }

    override fun findStudentByEmail(email: String): Student? {
        return hibernateStudentRepository
            .find("email = ?1 and deletedAt is NULL", email)
            .firstResult()
            ?.toDomain()
    }

    override fun createStudent(student: Student): Student {
        val classroom = hibernateClassroomRepository
            .findById(student.classroomId.toLong())
            ?: throw ClassroomNotExistsException()

        val student = student.toEntity(classroom);
        hibernateStudentRepository.persist(student)

        return student.toDomain()
    }
}