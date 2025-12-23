package com.techacademy.grades.application.usecase.subject

import com.techacademy.grades.application.dto.SubjectDTO

interface FindSubjectByNameUseCase {
    fun execute(name: String): SubjectDTO?
}