package com.techacademy.grades.application.service.subject

import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.mapper.subject.toDTO
import com.techacademy.grades.application.usecase.subject.FindSubjectUseCase
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindSubjectService(
    private val subjectRepository: SubjectRepositoryPort
): FindSubjectUseCase {
    override fun execute(id: Int): SubjectDTO? {
        return subjectRepository
            .findSubject(id)
            ?.toDTO()
    }
}