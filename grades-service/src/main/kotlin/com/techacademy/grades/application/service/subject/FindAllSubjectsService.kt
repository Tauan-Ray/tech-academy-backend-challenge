package com.techacademy.grades.application.service.subject

import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.mapper.subject.toDTO
import com.techacademy.grades.application.usecase.subject.FindAllSubjectsUseCase
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindAllSubjectsService(
    private val subjectRepository: SubjectRepositoryPort
): FindAllSubjectsUseCase {
    override fun execute(): List<SubjectDTO> {
        return subjectRepository
            .findAll()
            .map { it.toDTO() }
    }
}