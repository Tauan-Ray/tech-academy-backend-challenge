package com.techacademy.grades.application.subject

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.service.exception.ClassroomNotExistsException
import com.techacademy.grades.application.service.exception.SubjectAlreadyExistsException
import com.techacademy.grades.application.service.subject.CreateSubjectService
import com.techacademy.grades.domain.model.Subject
import com.techacademy.grades.domain.port.ClassroomLookupPort
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CreateSubjectServiceTest {
    private val subjectRepository: SubjectRepositoryPort = mock()
    private val classroomLookupPort: ClassroomLookupPort = mock()
    private val service = CreateSubjectService(subjectRepository, classroomLookupPort)

    @Test
    fun`deve lancar excecao caso exista disciplina com o mesmo nome`() {
        val dto = CreateSubjectDTO(
            name = "Matemática",
            type = "BASE",
            course = null,
            grade = 1,
            workload = 180,
        )

        whenever(subjectRepository.findSubjectByName(dto.name))
            .thenReturn(mock())

        assertThrows(SubjectAlreadyExistsException::class.java) {
            service.execute(dto)
        }

        verify(subjectRepository).findSubjectByName(dto.name)
        verify(classroomLookupPort, never()).existsByIdentity(any(),any())
        verify(subjectRepository, never()).createSubject(any())
    }

    @Test
    fun`deve lancar excecao caso turma selecionada nao exista`() {
        val dto = CreateSubjectDTO(
            name = "Fundamentos de administração",
            type = "COURSE_SPECIFIC",
            course = "Administração",
            grade = 1,
            workload = 180,
        )

        whenever(subjectRepository.findSubjectByName(dto.name))
            .thenReturn(null)

        whenever(classroomLookupPort.existsByIdentity(dto.course, dto.grade))
            .thenReturn(false)

        assertThrows(ClassroomNotExistsException::class.java) {
            service.execute(dto)
        }

        verify(subjectRepository).findSubjectByName(dto.name)
        verify(classroomLookupPort).existsByIdentity(dto.course,dto.grade)
        verify(subjectRepository, never()).createSubject(any())
    }

    @Test
    fun`deve criar disciplina com dados corretos`() {
        val dto = CreateSubjectDTO(
            name = "Matemática",
            type = "BASE",
            course = null,
            grade = 1,
            workload = 180,
        )

        whenever(subjectRepository.findSubjectByName(dto.name))
            .thenReturn(null)

        whenever(classroomLookupPort.existsByIdentity(dto.course, dto.grade))
            .thenReturn(true)

        whenever(subjectRepository.createSubject(any()))
            .thenAnswer { it.arguments[0] as Subject }

        val subjectCaptor = argumentCaptor<Subject>()

        service.execute(dto)

        verify(subjectRepository).findSubjectByName(dto.name)
        verify(classroomLookupPort).existsByIdentity(dto.course,dto.grade)
        verify(subjectRepository).createSubject(subjectCaptor.capture())

        val savedSubject = subjectCaptor.firstValue

        assertEquals(dto.name, savedSubject.name)
        assertEquals(dto.type, savedSubject.type.name)
        assertEquals(dto.course, savedSubject.course)
        assertEquals(dto.grade, savedSubject.grade)
        assertEquals(dto.workload, savedSubject.workload)
        assertEquals(savedSubject.createdAt, savedSubject.updatedAt)
    }
}