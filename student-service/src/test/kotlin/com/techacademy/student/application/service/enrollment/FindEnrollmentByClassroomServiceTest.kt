package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import com.techacademy.student.domain.model.Enrollment
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

class FindEnrollmentByClassroomServiceTest {

    private val enrollmentRepository: EnrollmentRepositoryPort = mock()
    private val classroomRepository: ClassroomRepositoryPort = mock()
    private val service =
        FindEnrollmentByClassroomService(enrollmentRepository, classroomRepository)

    @Test
    fun `deve lancar excecao se a turma nao existir`() {
        whenever(classroomRepository.findClassroom(1))
            .thenReturn(null)

        assertThrows(ClassroomNotExistsException::class.java) {
            service.execute(1)
        }

        verify(classroomRepository).findClassroom(1)
        verify(enrollmentRepository, never()).findByClassroom(any())
    }

    @Test
    fun `deve retornar lista de enrollments quando a turma existir`() {
        val classroomId = 1

        whenever(classroomRepository.findClassroom(classroomId))
            .thenReturn(mock())

        val enrollment = Enrollment(
            id = 10,
            studentId = 5,
            classroomId = classroomId,
            active = true,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        whenever(enrollmentRepository.findByClassroom(classroomId))
            .thenReturn(listOf(enrollment))

        val result = service.execute(classroomId)
        val firstEnrollment = result.first()

        assertEquals(1, result.size)
        assertEquals(enrollment.id, firstEnrollment.id)
        assertEquals(enrollment.studentId, firstEnrollment.studentId)
        assertEquals(enrollment.classroomId, firstEnrollment.classroomId)
        assertEquals(enrollment.active, firstEnrollment.active)

        verify(classroomRepository).findClassroom(classroomId)
        verify(enrollmentRepository).findByClassroom(classroomId)
    }
}
