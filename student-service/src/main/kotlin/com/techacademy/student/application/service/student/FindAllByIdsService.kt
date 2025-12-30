package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.StudentDTO
import com.techacademy.student.application.mapper.student.toDTO
import com.techacademy.student.application.usecase.student.FindAllByIdsUseCase
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindAllByIdsService(
    private val studentRepository: StudentRepositoryPort
): FindAllByIdsUseCase {

    override fun execute(studentIds: List<Int>): List<StudentDTO> {
         return studentRepository
             .findAllByIds(studentIds)
             .map { it.toDTO() }
    }
}