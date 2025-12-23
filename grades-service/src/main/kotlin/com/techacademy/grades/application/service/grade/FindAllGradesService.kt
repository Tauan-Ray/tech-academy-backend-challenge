package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.mapper.grade.toDTO
import com.techacademy.grades.application.usecase.grade.FindAllGradesUseCase
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindAllGradesService(
    private val gradeRepository: GradeRepositoryPort
): FindAllGradesUseCase {

    override fun execute(): List<GradeDTO> {
        return gradeRepository
            .findAll()
            .map { it.toDTO() }
    }
}