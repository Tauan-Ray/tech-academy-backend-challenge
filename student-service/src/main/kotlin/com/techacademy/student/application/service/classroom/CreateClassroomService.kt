package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.application.dto.CreateClassroomDTO
import com.techacademy.student.application.mapper.classroom.toDTO
import com.techacademy.student.application.mapper.classroom.toDomain
import com.techacademy.student.application.service.exception.ClassroomAlreadyExistsException
import com.techacademy.student.application.usecase.classroom.CreateClassroomUseCase
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class CreateClassroomService(
    private val classroomRepository: ClassroomRepositoryPort
): CreateClassroomUseCase {

    @Transactional
    override fun execute(createClassroom: CreateClassroomDTO): ClassroomDTO {
        val existingCourse = classroomRepository.findClassroomByIdentity(
            createClassroom.year,
            createClassroom.course,
            createClassroom.grade,
        )

        if (existingCourse != null) throw ClassroomAlreadyExistsException()

        val classroom = createClassroom.toDomain()
        val now = LocalDateTime.now()

        classroom.createdAt = now
        classroom.updatedAt = now

        val savedClassroom = classroomRepository.createClassroom(classroom)

        return savedClassroom.toDTO()
    }
}