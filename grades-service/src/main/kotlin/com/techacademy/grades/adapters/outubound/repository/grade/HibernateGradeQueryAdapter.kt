package com.techacademy.grades.adapters.outubound.repository.grade

import com.techacademy.grades.adapters.outubound.mapper.grade.toGradeWithSubjectDTO
import com.techacademy.grades.application.dto.GradeWithSubjectDTO
import com.techacademy.grades.application.port.GradeQueryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateGradeQueryAdapter(
    private val hibernateGradeRepository: HibernateGradeRepository,
): GradeQueryPort {

    override fun findGradesWithSubjectsByEnrollmentIds(enrollmentIds: List<Int>): List<GradeWithSubjectDTO> {
        return hibernateGradeRepository
            .find("enrollmentId IN ?1 AND deletedAt IS NULL", enrollmentIds)
            .list()
            .map { it.toGradeWithSubjectDTO() }
    }
}