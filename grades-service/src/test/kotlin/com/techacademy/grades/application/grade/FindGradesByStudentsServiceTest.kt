package com.techacademy.grades.application.grade

import com.techacademy.grades.application.dto.GradeWithSubjectDTO
import com.techacademy.grades.application.port.EnrollmentLookupPort
import com.techacademy.grades.application.port.GradeQueryPort
import com.techacademy.grades.application.service.exception.StudentNotExistsException
import com.techacademy.grades.application.service.grade.FindGradesByStudentsService
import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.port.StudentLookupPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class FindGradesByStudentsServiceTest {
    private val gradeQueryPort: GradeQueryPort = mock()
    private val studentLookupPort: StudentLookupPort = mock()
    private val enrollmentLookupPort: EnrollmentLookupPort = mock()
    private val service = FindGradesByStudentsService(
        gradeQueryPort,
        studentLookupPort,
        enrollmentLookupPort,
    )

    @Test
    fun`deve lancar excecao caso nao ache nenhum estudante com os ids fornecidos`() {
        val studentIds = listOf(1,2)

        whenever(studentLookupPort.findExistingIds(studentIds))
            .thenReturn(emptyList())

        assertThrows(StudentNotExistsException::class.java) {
            service.execute(studentIds)
        }

        verify(studentLookupPort).findExistingIds(studentIds)
        verify(enrollmentLookupPort, never()).findEnrollmentsByStudents(any())
        verify(gradeQueryPort, never()).findGradesWithSubjectsByEnrollmentIds(any())
    }

    @Test
    fun`deve retornar um Map vazio caso nao exista nenhuma matricula correspondente aos alunos`() {
        val studentIds = listOf(1,2)

        whenever(studentLookupPort.findExistingIds(studentIds))
            .thenReturn(listOf(2))

        whenever(
            enrollmentLookupPort.findEnrollmentsByStudents(listOf(2))
        ).thenReturn(mapOf(2 to emptyList()))

        val result = service.execute(studentIds)

        assertTrue(result.isEmpty())

        verify(studentLookupPort).findExistingIds(studentIds)
        verify(enrollmentLookupPort).findEnrollmentsByStudents(listOf(2))
        verify(gradeQueryPort, never()).findGradesWithSubjectsByEnrollmentIds(any())
    }

    @Test
    fun`deve retornar Map com as notas agrupadas por matricula`() {
        val studentIds = listOf(1, 2)
        val existingStudentIds = listOf(1, 2)

        val enrollmentsByStudent = mapOf(
            1 to listOf(10, 11),
            2 to listOf(20)
        )

        val allEnrollmentIds = listOf(10, 11, 20)

        val grade1 = GradeWithSubjectDTO(
            enrollmentId = 10,
            subjectId = 1,
            subjectName = "Matemática",
            bimester = Bimester.BIMESTER_1,
            score = BigDecimal("8.5"),
            grade = 1
        )

        val grade2 = GradeWithSubjectDTO(
            enrollmentId = 20,
            subjectId = 2,
            subjectName = "Português",
            bimester = Bimester.BIMESTER_1,
            score = BigDecimal(9.0),
            grade = 1
        )

        whenever(studentLookupPort.findExistingIds(studentIds))
            .thenReturn(existingStudentIds)

        whenever(enrollmentLookupPort.findEnrollmentsByStudents(existingStudentIds))
            .thenReturn(enrollmentsByStudent)

        whenever(
            gradeQueryPort.findGradesWithSubjectsByEnrollmentIds(allEnrollmentIds)
        ).thenReturn(listOf(grade1, grade2))

        val result = service.execute(studentIds)

        assertEquals(2, result.size)
        assertEquals(1, result[10]?.size)
        assertEquals(grade1, result[10]?.first())
        assertEquals(grade2, result[20]?.first())

        verify(studentLookupPort).findExistingIds(studentIds)
        verify(enrollmentLookupPort).findEnrollmentsByStudents(existingStudentIds)
        verify(gradeQueryPort).findGradesWithSubjectsByEnrollmentIds(allEnrollmentIds)
    }
}