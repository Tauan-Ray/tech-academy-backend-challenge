package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.model.Enrollment
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

class FindEnrollmentByStudentServiceTest {
    private val enrollmentRepository: EnrollmentRepositoryPort = mock()
    private val studentRepository: StudentRepositoryPort = mock()
    private val service = FindEnrollmentByStudentService(enrollmentRepository, studentRepository)

    @Test
    fun`deve lancar excecao se o estudante nao existir`() {
        whenever(studentRepository.findStudent(1))
            .thenReturn(null)

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(1)
        }

        verify(studentRepository).findStudent(1)
        verify(enrollmentRepository, never()).findByStudent(any())
    }

    @Test
    fun `deve retornar lista de enrollments quando estudante existir`() {
        val studentId = 1

        whenever(studentRepository.findStudent(studentId))
            .thenReturn(mock())

        val enrollment = Enrollment(
            id = 10,
            studentId = studentId,
            classroomId = 3,
            active = true,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        whenever(enrollmentRepository.findByStudent(studentId))
            .thenReturn(listOf(enrollment))

        val result = service.execute(studentId)
        val firstEnrollment = result.first()

        assertEquals(1, result.size)
        assertEquals(enrollment.id, firstEnrollment.id)
        assertEquals(enrollment.studentId, firstEnrollment.studentId)
        assertEquals(enrollment.classroomId, firstEnrollment.classroomId)
        assertEquals(enrollment.active, firstEnrollment.active)

        verify(studentRepository).findStudent(studentId)
        verify(enrollmentRepository).findByStudent(studentId)
    }
}