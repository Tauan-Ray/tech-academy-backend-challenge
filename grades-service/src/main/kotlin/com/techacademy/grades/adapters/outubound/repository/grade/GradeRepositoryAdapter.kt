package com.techacademy.grades.adapters.outubound.repository.grade

import com.techacademy.grades.adapters.outubound.mapper.grade.toDomain
import com.techacademy.grades.domain.model.Grade
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeRepositoryAdapter(
    private val hibernateGradeRepository: HibernateGradeRepository
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

    override fun findGradeByStudent(id: Int): Grade? {
        return hibernateGradeRepository
            .find("studentId = ?1", id)
            .firstResult()
            ?.toDomain()
    }
}