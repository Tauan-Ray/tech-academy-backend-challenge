package com.techacademy.grades.adapters.outubound.repository.grade

import com.techacademy.grades.adapters.outubound.mapper.grade.toDomain
import com.techacademy.grades.adapters.outubound.mapper.grade.toEntity
import com.techacademy.grades.adapters.outubound.repository.subject.HibernateSubjectRepository
import com.techacademy.grades.application.service.exception.SubjectNotExistsException
import com.techacademy.grades.domain.model.Grade
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeRepositoryAdapter(
    private val hibernateGradeRepository: HibernateGradeRepository,
    private val hibernateSubjectRepository: HibernateSubjectRepository
): GradeRepositoryPort {
    override fun findAll(): List<Grade> {
        return hibernateGradeRepository
            .find("deletedAt IS NULL")
            .list()
            .map { it.toDomain() }
    }

    override fun findGrade(id: Int): Grade? {
        return hibernateGradeRepository
            .findById(id.toLong())
            ?.toDomain()
    }

    override fun findGradeByStudent(id: Int): List<Grade> {
        return hibernateGradeRepository
            .find("studentId = ?1 and deletedAt IS NULL", id)
            .list()
            .map { it.toDomain() }
    }

    override fun createGrade(grade: Grade): Grade {
        val subject = hibernateSubjectRepository
            .findById(grade.subjectId.toLong())
            ?: throw SubjectNotExistsException()

        println(grade)
        val entity = grade.toEntity(subject)
        hibernateGradeRepository.persist(entity)

        return entity.toDomain()
    }
}