package com.techacademy.grades.application.usecase.subject

import com.techacademy.grades.application.dto.SubjectDTO

interface FindAllSubjectsUseCase {
    fun execute(): List<SubjectDTO>
}