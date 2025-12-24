package com.techacademy.grades.application.service.subject

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.mapper.subject.toDTO
import com.techacademy.grades.application.mapper.subject.toDomain
import com.techacademy.grades.application.service.exception.ClassroomNotExistsException
import com.techacademy.grades.application.service.exception.SubjectAlreadyExistsException
import com.techacademy.grades.application.usecase.subject.CreateSubjectUseCase
import com.techacademy.grades.application.util.validateCourseByType
import com.techacademy.grades.domain.port.ClassroomLookupPort
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class CreateSubjectService(
    private val subjectRepository: SubjectRepositoryPort,
    private val classroomLookupPort: ClassroomLookupPort,
): CreateSubjectUseCase {

    @Transactional
    override fun execute(createSubject: CreateSubjectDTO): SubjectDTO {
        validateCourseByType(createSubject)

        val existingSubject = subjectRepository.findSubjectByName(createSubject.name)
        if (existingSubject != null) throw SubjectAlreadyExistsException()

        val existingClassroom = classroomLookupPort
            .existsByIdentity(createSubject.course, createSubject.grade)

        if (!existingClassroom) throw ClassroomNotExistsException()

        val newSubject = createSubject.toDomain()
        val now = LocalDateTime.now()

        newSubject.createdAt = now
        newSubject.updatedAt = now

        val savedSubject = subjectRepository.createSubject(newSubject)

        return savedSubject.toDTO()
    }
}