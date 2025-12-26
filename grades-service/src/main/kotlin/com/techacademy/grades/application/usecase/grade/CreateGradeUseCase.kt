package com.techacademy.grades.application.usecase.grade

import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.application.dto.GradeDTO

interface CreateGradeUseCase {
    fun execute(createGrade: CreateGradeDTO): GradeDTO
}