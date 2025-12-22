package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.mapper.classroom.toDTO
import com.techacademy.student.application.usecase.classroom.FindClassroomByIdentityUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort

class FindClassroomByIdentityService(
    private val classroomRepository: ClassroomRepositoryPort
): FindClassroomByIdentityUseCase {
    override fun execute(year: Int, course: String, grade: Int): ClassroomDTO? {
        return classroomRepository
            .findClassroomByIdentity(year, course, grade)
            ?.toDTO()
    }
}