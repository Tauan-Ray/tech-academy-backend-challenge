package com.techacademy.grades.application.service.subject

import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.mapper.subject.toDTO
import com.techacademy.grades.application.usecase.subject.FindSubjectByNameUseCase
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindSubjectByNameService(
    private val subjectRepository: SubjectRepositoryPort
): FindSubjectByNameUseCase {

    override fun execute(name: String): SubjectDTO? {
        return subjectRepository
            .findSubjectByName(name)
            ?.toDTO()
    }
}