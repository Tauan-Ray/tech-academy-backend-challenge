package com.techacademy.grades.application.usecase.grade

import com.techacademy.grades.application.dto.GradeDTO

interface FindAllGradesUseCase {
    fun execute(): List<GradeDTO>
}