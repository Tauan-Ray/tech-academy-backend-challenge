package com.techacademy.grades.application.usecase.subject

import com.techacademy.grades.application.dto.SubjectDTO

interface FindSubjectUseCase {
    fun execute(id: Int): SubjectDTO?
}