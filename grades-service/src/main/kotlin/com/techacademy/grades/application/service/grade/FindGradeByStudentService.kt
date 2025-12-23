package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.mapper.grade.toDTO
import com.techacademy.grades.application.service.exception.StudentNotExistsException
import com.techacademy.grades.application.usecase.grade.FindGradeByStudentUseCase
import com.techacademy.grades.domain.port.StudentLookupPort
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindGradeByStudentService(
    private val gradeRepository: GradeRepositoryPort,
    private val studentLookupPort: StudentLookupPort,
): FindGradeByStudentUseCase {

    override fun execute(id: Int): GradeDTO? {
        val existsStudent = studentLookupPort.existsById(id)
        if (!existsStudent) throw StudentNotExistsException()

        return gradeRepository
            .findGradeByStudent(id)
            ?.toDTO()
    }
}