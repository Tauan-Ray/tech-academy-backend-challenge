package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.dto.classroom.CreateClassroomDTO
import com.techacademy.student.application.service.exception.ClassroomAlreadyExistsException
import com.techacademy.student.domain.model.Classroom
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.mock
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CreateClassroomServiceTest {
    private val repository: ClassroomRepositoryPort = mock()
    private val service = CreateClassroomService(repository)

    @Test
    fun`deve criar turma caso nao exista uma com as mesmas caracteristicas`() {
        val dto = CreateClassroomDTO(
            year = 2025,
            course = "Informática",
            grade = 3
        )

        whenever(
            repository.findClassroomByIdentity(dto.year, dto.course, dto.grade)
        ).thenReturn(emptyList())

        whenever(repository.createClassroom(any()))
            .thenAnswer { it.arguments[0] as Classroom }

        val classroomCaptor = argumentCaptor<Classroom>()

        service.execute(dto)

        verify(repository).findClassroomByIdentity(dto.year, dto.course, dto.grade)
        verify(repository).createClassroom(classroomCaptor.capture())

        val savedClassroom = classroomCaptor.firstValue

        assertEquals(dto.year, savedClassroom.year)
        assertEquals(dto.course, savedClassroom.course)
        assertEquals(dto.grade, savedClassroom.grade)
        assertEquals(savedClassroom.createdAt, savedClassroom.updatedAt)
    }

    @Test
    fun`deve lancar excecao caso ja exista uma turma com as mesma caracteristicas`() {
        val dto = CreateClassroomDTO(
            year = 2025,
            course = "Informática",
            grade = 3
        )

        whenever(
            repository.findClassroomByIdentity(dto.year, dto.course, dto.grade)
        ).thenReturn(mock())

        assertThrows(ClassroomAlreadyExistsException::class.java) {
            service.execute(dto)
        }

        verify(repository).findClassroomByIdentity(dto.year, dto.course, dto.grade)
        verify(repository, never()).createClassroom(any())
    }
}