package com.techacademy.grades.adapters.outubound.repository.grade

import com.techacademy.grades.adapters.outubound.mapper.grade.toDomain
import com.techacademy.grades.adapters.outubound.mapper.grade.toEntity
import com.techacademy.grades.adapters.outubound.repository.subject.HibernateSubjectRepository
import com.techacademy.grades.application.service.exception.SubjectNotExistsException
import com.techacademy.grades.domain.model.Bimester
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

    override fun findGradeByEnrollmentIds(enrollmentIds: List<Int>): List<Grade> {
        return hibernateGradeRepository
            .find("enrollmentId IN ?1 and deletedAt IS NULL", enrollmentIds)
            .list()
            .map { it.toDomain() }
    }

    override fun findExistingGrades(enrollmentId: Int?, subjectId: Int?, bimester: Bimester?): List<Grade> {
        return hibernateGradeRepository
            .find(
                """
                    (:enrollmentId IS NULL OR enrollmentId = :enrollmentId)
                    AND (:subjectId IS NULL OR subject.id = :subjectId)
                    AND (:bimester IS NULL OR bimester = :bimester)
                """.trimIndent(),
                mapOf(
                    "enrollmentId" to enrollmentId,
                    "subjectId" to subjectId,
                    "bimester" to bimester
                )
            )
            .list()
            .map { it.toDomain() }
    }

    override fun createGrade(grade: Grade): Grade {
        val subject = hibernateSubjectRepository
            .findById(grade.subjectId.toLong())
            ?: throw SubjectNotExistsException()

        val entity = grade.toEntity(subject)
        hibernateGradeRepository.persist(entity)

        return entity.toDomain()
    }
}