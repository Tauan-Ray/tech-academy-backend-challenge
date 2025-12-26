package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.mapper.grade.toDTO
import com.techacademy.grades.application.mapper.grade.toDomain
import com.techacademy.grades.application.service.exception.StudentNotExistsException
import com.techacademy.grades.application.service.exception.SubjectNotExistsException
import com.techacademy.grades.application.usecase.grade.CreateGradeUseCase
import com.techacademy.grades.domain.port.StudentLookupPort
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class CreateGradeService(
    private val gradeRepository: GradeRepositoryPort,
    private val subjectRepository: SubjectRepositoryPort,
    private val studentLookupPort: StudentLookupPort,
): CreateGradeUseCase {

    @Transactional
    override fun execute(createGrade: CreateGradeDTO): GradeDTO {
        val existingStudent = studentLookupPort
            .existsById(createGrade.studentId)

        if (!existingStudent) throw StudentNotExistsException()

        subjectRepository
            .findSubject(createGrade.subjectId)
            ?: throw SubjectNotExistsException()

        val newGrade = createGrade.toDomain()
        val now = LocalDateTime.now()

        newGrade.createdAt = now
        newGrade.updatedAt = now

        val savedGrade = gradeRepository.createGrade(newGrade)

        return savedGrade.toDTO()
    }
}