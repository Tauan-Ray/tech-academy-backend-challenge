package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.mapper.grade.toDTO
import com.techacademy.grades.application.service.exception.MissingGradeFilterException
import com.techacademy.grades.application.usecase.grade.FindExistingGradesUseCase
import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindExistingGradesService(
    private val gradeRepository: GradeRepositoryPort
): FindExistingGradesUseCase {

    override fun execute(studentId: Int?, subjectId: Int?, bimester: Bimester?): List<GradeDTO> {
        if (listOf(studentId, subjectId, bimester).all { it == null }){
            throw MissingGradeFilterException()
        }

        return gradeRepository
            .findExistingGrades(studentId, subjectId, bimester)
            .map { it.toDTO() }
    }
}