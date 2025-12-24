package com.techacademy.grades.application.usecase.subject

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.dto.SubjectDTO

interface CreateSubjectUseCase {
    fun execute(createSubject: CreateSubjectDTO): SubjectDTO
}