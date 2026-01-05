package com.techacademy.student.application.service.student

import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DeleteStudentServiceTest {
    private val repository: StudentRepositoryPort = mock()
    private val service = DeleteStudentService(repository)

    @Test
    fun `deve deletar estudante quando existir`() {
        val studentId = 1
        whenever(repository.findStudent(studentId))
            .thenReturn(mock())

        service.execute(studentId)
        verify(repository).deleteStudent(studentId)
    }

    @Test
    fun `deve lancar excecao quando estudante nao existir`() {
        whenever(repository.findStudent(any()))
            .thenReturn(null)

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(1)
        }
        verify(repository, never()).deleteStudent(any())
    }
}