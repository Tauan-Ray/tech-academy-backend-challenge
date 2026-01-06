package com.techacademy.grades.application.grade

import com.techacademy.grades.application.port.EnrollmentLookupPort
import com.techacademy.grades.application.service.exception.StudentNotExistsException
import com.techacademy.grades.application.service.grade.FindGradeByStudentService
import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.port.StudentLookupPort
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class FindGradeByStudentServiceTest {
    private val gradeRepository: GradeRepositoryPort = mock()
    private val studentLookupPort: StudentLookupPort = mock()
    private val enrollmentLookupPort: EnrollmentLookupPort = mock()
    private val service = FindGradeByStudentService(
        gradeRepository,
        studentLookupPort,
        enrollmentLookupPort,
    )

    @Test
    fun`deve lancar excecao se o aluno nao existir`() {
        val studentId = 1

        whenever(studentLookupPort.existsById(studentId))
            .thenReturn(false)

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(studentId)
        }

        verify(studentLookupPort).existsById(studentId)
        verify(enrollmentLookupPort, never()).findEnrollmentsByStudent(any())
        verify(gradeRepository, never()).findGradeByEnrollmentIds(any())
    }

    @Test
    fun`deve retornar uma lista vazia caso o aluno nao possua nenhuma matricula`() {
        val studentId = 1

        whenever(studentLookupPort.existsById(studentId))
            .thenReturn(true)

        val result = service.execute(studentId)

        assertEquals(0, result.size)

        verify(studentLookupPort).existsById(studentId)
        verify(enrollmentLookupPort).findEnrollmentsByStudent(studentId)
        verify(gradeRepository, never()).findGradeByEnrollmentIds(any())
    }

    @Test
    fun `deve retornar lista de notas quando aluno existir e possuir matriculas`() {
        val studentId = 1
        val enrollmentIds = listOf(10, 20)

        whenever(studentLookupPort.existsById(studentId))
            .thenReturn(true)

        whenever(enrollmentLookupPort.findEnrollmentsByStudent(studentId))
            .thenReturn(enrollmentIds)

        val grade = com.techacademy.grades.domain.model.Grade(
            id = 1,
            enrollmentId = 10,
            subjectId = 2,
            bimester = Bimester.BIMESTER_1,
            score = BigDecimal(9.5)
        )

        whenever(
            gradeRepository.findGradeByEnrollmentIds(enrollmentIds)
        ).thenReturn(listOf(grade))

        val result = service.execute(studentId)

        assertEquals(1, result.size)
        assertEquals(grade.id, result.first().id)
        assertEquals(grade.score, result.first().score)
        assertEquals(grade.bimester, result.first().bimester)

        verify(studentLookupPort).existsById(studentId)
        verify(enrollmentLookupPort).findEnrollmentsByStudent(studentId)
        verify(gradeRepository).findGradeByEnrollmentIds(enrollmentIds)
    }

}