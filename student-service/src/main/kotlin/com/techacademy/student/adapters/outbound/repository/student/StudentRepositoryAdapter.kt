package com.techacademy.student.adapters.outbound.repository.student

import com.techacademy.student.adapters.outbound.mapper.student.toDomain
import com.techacademy.student.adapters.outbound.mapper.student.toEntity
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import java.time.LocalDateTime

@ApplicationScoped
class StudentRepositoryAdapter(
    private val hibernateStudentRepository: HibernateStudentRepository,
): StudentRepositoryPort {
    override fun findAll(): List<Student> {
        return hibernateStudentRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findAllByIds(studentIds: List<Int>): List<Student> {
        return hibernateStudentRepository
            .find("id IN ?1 AND deletedAt IS NULL", studentIds)
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
        val student = student.toEntity();
        hibernateStudentRepository.persist(student)

        return student.toDomain()
    }

    override fun updateStudent(student: Student): Student {
        val studentId = student.id ?: throw StudentNotExistsException()
        val entity = hibernateStudentRepository.findById(studentId.toLong())
            ?: throw StudentNotExistsException()

        entity.name = student.name
        entity.email = student.email

        return entity.toDomain()
    }

    override fun deleteStudent(id: Int) {
        val entity = hibernateStudentRepository
            .find("id = ?1 AND deletedAt IS NULL", id)
            .firstResult()
            ?: throw StudentNotExistsException()

        entity.deletedAt = LocalDateTime.now()
    }
}