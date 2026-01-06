package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ExistsEnrollmentActiveByStudentServiceTest {

    private val enrollmentRepository: EnrollmentRepositoryPort = mock()
    private val studentRepository: StudentRepositoryPort = mock()
    private val service = ExistsEnrollmentActiveByStudentService(
        enrollmentRepository,
        studentRepository
    )

    @Test
    fun `deve lancar excecao quando estudante nao existir`() {
        whenever(studentRepository.findStudent(any()))
            .thenReturn(null)

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(1)
        }

        verify(studentRepository).findStudent(1)
        verify(enrollmentRepository, never()).existsActiveByStudent(any())
    }

    @Test
    fun `deve retornar true quando existir matricula ativa`() {
        whenever(studentRepository.findStudent(1))
            .thenReturn(mock())

        whenever(enrollmentRepository.existsActiveByStudent(1))
            .thenReturn(true)

        val result = service.execute(1)

        assertEquals(true, result)

        verify(studentRepository).findStudent(1)
        verify(enrollmentRepository).existsActiveByStudent(1)
    }
}
