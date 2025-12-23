package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.mapper.grade.toDTO
import com.techacademy.grades.application.usecase.grade.FindGradeUseCase
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindGradeService(
    private val gradeRepository: GradeRepositoryPort
): FindGradeUseCase {

    override fun execute(id: Int): GradeDTO? {
        return gradeRepository
            .findGrade(id)
            ?.toDTO()
    }
}