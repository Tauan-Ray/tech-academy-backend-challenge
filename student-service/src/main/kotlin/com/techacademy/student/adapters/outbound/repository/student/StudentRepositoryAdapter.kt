package com.techacademy.student.adapters.outbound.repository.student

import com.techacademy.student.adapters.outbound.mapper.student.toDomain
import com.techacademy.student.adapters.outbound.mapper.student.toEntity
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentRepositoryAdapter(
    private val hibernateStudentRepository: HibernateStudentRepository
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
        println(student)
        val student = student.toEntity();
        hibernateStudentRepository.persist(student)

        return student.toDomain()
    }
}