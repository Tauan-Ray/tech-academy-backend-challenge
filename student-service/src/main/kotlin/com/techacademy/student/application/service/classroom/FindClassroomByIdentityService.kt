package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.mapper.classroom.toDTO
import com.techacademy.student.application.service.exception.MissingClassroomFilterException
import com.techacademy.student.application.usecase.classroom.FindClassroomByIdentityUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindClassroomByIdentityService(
    private val classroomRepository: ClassroomRepositoryPort
): FindClassroomByIdentityUseCase {
    override fun execute(year: Int?, course: String?, grade: Int?): List<ClassroomDTO> {
        if (listOf(year, course, grade).all { it == null }) {
            throw MissingClassroomFilterException()
        }

        return classroomRepository
            .findClassroomByIdentity(year, course, grade)
            .map { it.toDTO() }
    }
}