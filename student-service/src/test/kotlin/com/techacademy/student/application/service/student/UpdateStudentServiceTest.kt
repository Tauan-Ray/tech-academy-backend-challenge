package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.student.UpdateStudentDTO
import com.techacademy.student.application.service.exception.MissingDataUpdateStudentException
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.never

class UpdateStudentServiceTest {
    private val repository: StudentRepositoryPort = mock()
    private val service = UpdateStudentService(repository)

    @Test
    fun`deve atualizar estudante existente com dados corretos`() {
        val studentId = 1
        val student = Student(
            id = studentId,
            name = "Old Name",
            email = "old@email.com"
        )
        val dto = UpdateStudentDTO(
            "John",
            "john.example.com"
        )

        whenever(repository.findStudent(studentId))
            .thenReturn(student)

        whenever(repository.updateStudent(student))
            .thenReturn(student)

        service.execute(studentId, dto)

        assertEquals("John", student.name)
        assertEquals("john.example.com", student.email)

        verify(repository).findStudent(studentId)
        verify(repository).updateStudent(student)
    }

    @Test
    fun`deve lancar excecao quando nenhum dado de atualizacao for informado`() {
        val studentId = 1
        val dto = UpdateStudentDTO()

        assertThrows(MissingDataUpdateStudentException::class.java) {
            service.execute(studentId, dto)
        }

        verify(repository, never()).updateStudent(any())
    }

    @Test
    fun`deve lancar excecao quando o estudante nao existir`() {
        val studentId = 1
        val dto = UpdateStudentDTO(
            "John",
            "john.example.com"
        )

        whenever(repository.findStudent(any()))
            .thenReturn(null)

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(studentId, dto)
        }

        verify(repository).findStudent(studentId)
        verify(repository, never()).updateStudent(any())
    }
}