package com.techacademy.grades.application.usecase.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.domain.model.Bimester

interface FindExistingGradesUseCase {
    fun execute(
        studentId: Int?,
        subjectId: Int?,
        bimester: Bimester?
    ): List<GradeDTO>
}