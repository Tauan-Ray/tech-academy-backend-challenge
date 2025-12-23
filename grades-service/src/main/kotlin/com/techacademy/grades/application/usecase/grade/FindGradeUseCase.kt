package com.techacademy.grades.application.usecase.grade

import com.techacademy.grades.application.dto.GradeDTO

interface FindGradeUseCase {
    fun execute(id: Int): GradeDTO?
}