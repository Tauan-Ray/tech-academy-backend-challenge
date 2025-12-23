package com.techacademy.grades.application.usecase.grade

import com.techacademy.grades.application.dto.GradeDTO

interface FindGradeByStudentUseCase {
    fun execute(id: Int): GradeDTO?
}