package com.techacademy.student.application.service.enrollment

import com.techacademy.student.application.dto.enrollment.CreateEnrollmentDTO
import com.techacademy.student.application.service.exception.ActiveEnrollmentException
import com.techacademy.student.application.service.exception.ClassroomNotExistsException
import com.techacademy.student.application.service.exception.EnrollmentAlreadyExistsException
import com.techacademy.student.application.service.exception.StudentNotExistsException
import com.techacademy.student.domain.model.Enrollment
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import com.techacademy.student.domain.repository.EnrollmentRepositoryPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CreateEnrollmentServiceTest {
    private val enrollmentRepository: EnrollmentRepositoryPort = mock()
    private val studentRepository: StudentRepositoryPort = mock()
    private val classroomRepository: ClassroomRepositoryPort = mock()
    private val service = CreateEnrollmentService(
        enrollmentRepository,
        studentRepository,
        classroomRepository
    )

    @Test
    fun`deve lancar excecao se o estudante nao existir`() {
        val dto = CreateEnrollmentDTO(
            studentId = 1,
            classroomId = 1,
        )
        whenever(studentRepository.findStudent(dto.studentId))
            .thenReturn(null)

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(dto)
        }

        verify(studentRepository).findStudent(1)
        verify(classroomRepository, never()).findClassroom(any())
        verify(enrollmentRepository, never()).existsByStudentAndClassroom(any(), any())
        verify(enrollmentRepository, never()).existsActiveByStudent(any())
        verify(enrollmentRepository, never()).createEnrollment(any())
    }

    @Test
    fun`deve lancar excecao se a turma nao existir`() {
        val dto = CreateEnrollmentDTO(
            studentId = 1,
            classroomId = 1,
        )
        whenever(studentRepository.findStudent(dto.studentId))
            .thenReturn(mock())

        whenever(classroomRepository.findClassroom(dto.classroomId))
            .thenReturn(null)

        assertThrows(ClassroomNotExistsException::class.java) {
            service.execute(dto)
        }

        verify(studentRepository).findStudent(dto.studentId)
        verify(classroomRepository).findClassroom(dto.classroomId)
        verify(enrollmentRepository, never()).existsByStudentAndClassroom(any(), any())
        verify(enrollmentRepository, never()).existsActiveByStudent(any())
        verify(enrollmentRepository, never()).createEnrollment(any())
    }

    @Test
    fun`deve lancar excecao se ja existir uma matricula com o mesmo aluno e turma`() {
        val dto = CreateEnrollmentDTO(
            studentId = 1,
            classroomId = 1,
        )
        whenever(studentRepository.findStudent(dto.studentId))
            .thenReturn(mock())

        whenever(classroomRepository.findClassroom(dto.classroomId))
            .thenReturn(mock())

        whenever(
            enrollmentRepository.existsByStudentAndClassroom(dto.studentId, dto.classroomId)
        ).thenReturn(true)

        assertThrows(EnrollmentAlreadyExistsException::class.java) {
            service.execute(dto)
        }

        verify(studentRepository).findStudent(dto.studentId)
        verify(classroomRepository).findClassroom(dto.classroomId)
        verify(enrollmentRepository).existsByStudentAndClassroom(dto.studentId, dto.classroomId)
        verify(enrollmentRepository, never()).existsActiveByStudent(any())
        verify(enrollmentRepository, never()).createEnrollment(any())
    }

    @Test
    fun`deve lancar excecao se o aluno possuir uma matricula ativa`() {
        val dto = CreateEnrollmentDTO(
            studentId = 1,
            classroomId = 1,
        )
        whenever(studentRepository.findStudent(dto.studentId))
            .thenReturn(mock())

        whenever(classroomRepository.findClassroom(dto.classroomId))
            .thenReturn(mock())

        whenever(
            enrollmentRepository.existsByStudentAndClassroom(dto.studentId, dto.classroomId)
        ).thenReturn(false)

        whenever(enrollmentRepository.existsActiveByStudent(dto.studentId))
            .thenReturn(true)

        assertThrows(ActiveEnrollmentException::class.java) {
            service.execute(dto)
        }

        verify(studentRepository).findStudent(dto.studentId)
        verify(classroomRepository).findClassroom(dto.classroomId)
        verify(enrollmentRepository).existsByStudentAndClassroom(dto.studentId, dto.classroomId)
        verify(enrollmentRepository).existsActiveByStudent(dto.studentId)
        verify(enrollmentRepository, never()).createEnrollment(any())
    }

    @Test
    fun`deve criar uma matricula com os dados corretos`() {
        val dto = CreateEnrollmentDTO(
            studentId = 1,
            classroomId = 1
        )

        whenever(studentRepository.findStudent(dto.studentId))
            .thenReturn(mock())

        whenever(classroomRepository.findClassroom(dto.classroomId))
            .thenReturn(mock())

        whenever(
            enrollmentRepository.existsByStudentAndClassroom(dto.studentId, dto.classroomId)
        ).thenReturn(false)

        whenever(enrollmentRepository.existsActiveByStudent(dto.studentId))
            .thenReturn(false)

        whenever(enrollmentRepository.createEnrollment(any()))
            .thenAnswer { it.arguments[0] as Enrollment }

        val enrollmentCaptor = argumentCaptor<Enrollment>()

        service.execute(dto)

        verify(studentRepository).findStudent(dto.studentId)
        verify(classroomRepository).findClassroom(dto.classroomId)
        verify(enrollmentRepository).existsByStudentAndClassroom(dto.studentId, dto.classroomId)
        verify(enrollmentRepository).existsActiveByStudent(dto.studentId)
        verify(enrollmentRepository).createEnrollment(enrollmentCaptor.capture())

        val savedEnrollment = enrollmentCaptor.firstValue

        assertEquals(dto.studentId, savedEnrollment.studentId)
        assertEquals(dto.classroomId, savedEnrollment.classroomId)
        assertEquals(savedEnrollment.createdAt, savedEnrollment.updatedAt)
    }
}