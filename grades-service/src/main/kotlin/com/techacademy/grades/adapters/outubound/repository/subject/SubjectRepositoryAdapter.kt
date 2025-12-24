package com.techacademy.grades.adapters.outubound.repository.subject

import com.techacademy.grades.adapters.outubound.mapper.subject.toDomain
import com.techacademy.grades.adapters.outubound.mapper.subject.toEntity
import com.techacademy.grades.domain.model.Subject
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SubjectRepositoryAdapter(
    private val hibernateSubjectRepository: HibernateSubjectRepository
): SubjectRepositoryPort {

    override fun findAll(): List<Subject> {
        return hibernateSubjectRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findSubject(id: Int): Subject? {
        return hibernateSubjectRepository
            .findById(id.toLong())
            ?.toDomain()
    }

    override fun findSubjectByName(name: String): Subject? {
        return hibernateSubjectRepository
            .find("name = ?1", name)
            .firstResult()
            ?.toDomain()
    }

    override fun createSubject(subject: Subject): Subject {
        val entity = subject.toEntity()
        hibernateSubjectRepository.persist(entity)

        return entity.toDomain()
    }
}