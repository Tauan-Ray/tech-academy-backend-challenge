package com.techacademy.grades.application.usecase.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.dto.GradeWithSubjectDTO

interface FindGradesByStudentsUseCase {
    fun execute(studentIds: List<Int>): Map<Int, List<GradeWithSubjectDTO>>
}