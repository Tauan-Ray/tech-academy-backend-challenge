package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.student.CreateStudentDTO
import com.techacademy.student.application.service.exception.EmailAlreadyExistsException
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.never

class CreateStudentServiceTest {
    private val repository: StudentRepositoryPort = mock()
    private val service = CreateStudentService(repository)

    @Test
    fun `deve criar estudante quando email nao estiver em uso`() {
        val dto = CreateStudentDTO(
            name = "John",
            email = "john.example.com"
        )

        whenever(repository.findStudentByEmail(dto.email))
            .thenReturn(null)

        whenever(repository.createStudent(any()))
            .thenAnswer { it.arguments[0] as Student }

        val studentCaptor = argumentCaptor<Student>()

        service.execute(dto)

        verify(repository).findStudentByEmail(dto.email)
        verify(repository).createStudent(studentCaptor.capture())

        val savedStudent = studentCaptor.firstValue

        assertEquals(dto.name, savedStudent.name)
        assertEquals(dto.email, savedStudent.email)
        assertEquals(savedStudent.createdAt, savedStudent.updatedAt)
    }

    @Test
    fun`deve lancar excecao caso email ja esteja em uso`() {
        val dto = CreateStudentDTO(
            name = "existing Name",
            email = "existing@email.com"
        )

        whenever(repository.findStudentByEmail(dto.email))
            .thenReturn(mock())

        assertThrows(EmailAlreadyExistsException::class.java) {
            service.execute(dto)
        }

        verify(repository).findStudentByEmail(dto.email)
        verify(repository, never()).createStudent(any())
    }
}