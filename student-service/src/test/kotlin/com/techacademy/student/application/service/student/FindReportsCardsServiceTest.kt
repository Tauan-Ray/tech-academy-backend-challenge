package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.GradeResponseDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentClassroomDTO
import com.techacademy.student.application.dto.student.Bimester
import com.techacademy.student.application.port.EnrollmentQueryPort
import com.techacademy.student.domain.model.Student
import com.techacademy.student.domain.port.GradeLookupPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import java.math.BigDecimal

class FindReportsCardsServiceTest {
    private val repository: StudentRepositoryPort = mock()
    private val enrollmentQueryPort: EnrollmentQueryPort = mock()
    private val gradeLookupPort: GradeLookupPort = mock()
    private val service = FindReportsCardsService(
        repository,
        enrollmentQueryPort,
        gradeLookupPort,
    )

    @Test
    fun`deve retornar boletim de aluno com sucesso`() {
        val studentIds = listOf(1)
        val student = Student(
            id = 1,
            name = "John",
            email = "john@example.com"
        )
        val enrollmentClassroom = EnrollmentClassroomDTO(
            enrollmentId = 1,
            active = true,
            studentId = 1,
            classroomId = 1,
            year = 2025,
            course = "Informática",
            grade = 3
        )
        val gradeResponse = GradeResponseDTO(
            enrollmentId = 1,
            subjectId = 1,
            subjectName = "Matemática",
            grade = 3,
            Bimester.BIMESTER_1,
            score = BigDecimal(7.6)
        )
        val gradeLookupResponse = mapOf(1 to listOf(gradeResponse))

        whenever(repository.findAllByIds(studentIds))
            .thenReturn(listOf(student))

        whenever(
            enrollmentQueryPort.findEnrollmentWithClassroomByStudentIds(listOf(1))
        ).thenReturn(listOf(enrollmentClassroom))

        whenever(gradeLookupPort.findGradesByStudents(listOf(1)))
            .thenReturn(gradeLookupResponse)

        val result = service.execute(studentIds)
        val reportCard = result.first()
        val subject = reportCard.subjects.first()

        assertEquals(1, result.size)

        assertEquals(student.name, reportCard.name)
        assertEquals(enrollmentClassroom.enrollmentId, reportCard.enrollmentId)
        assertEquals(enrollmentClassroom.course, reportCard.course)
        assertEquals(enrollmentClassroom.year, reportCard.year)
        assertEquals(enrollmentClassroom.grade, reportCard.grade)
        assertEquals(enrollmentClassroom.active, reportCard.active)

        assertEquals(1, reportCard.subjects.size)

        assertEquals(gradeResponse.subjectName, subject.name)
        assertEquals(gradeResponse.grade, subject.grade)
        assertEquals(gradeResponse.bimester, subject.bimester)
        assertEquals(gradeResponse.score, subject.score)

        verify(repository).findAllByIds(studentIds)
        verify(enrollmentQueryPort).findEnrollmentWithClassroomByStudentIds(studentIds)
        verify(gradeLookupPort).findGradesByStudents(studentIds)
    }

    @Test
    fun`deve retornar uma lista vazia caso students nao possua nenhuma informacao`() {
        val studentIds = listOf(1)

        whenever(repository.findAllByIds(studentIds))
            .thenReturn(emptyList())

        val result = service.execute(studentIds)

        assertEquals(0, result.size)
        verify(repository).findAllByIds(studentIds)
        verify(enrollmentQueryPort, never()).findEnrollmentWithClassroomByStudentIds(any())
        verify(gradeLookupPort, never()).findGradesByStudents(any())
    }

    @Test
    fun`deve retornar uma lista vazia caso o aluno nao tenha matricula`() {
        val studentIds = listOf(1)
        val student = Student(
            id = 1,
            name = "John",
            email = "john@example.com"
        )

        whenever(repository.findAllByIds(studentIds))
            .thenReturn(listOf(student))

        whenever(
            enrollmentQueryPort.findEnrollmentWithClassroomByStudentIds(listOf(1))
        ).thenReturn(emptyList())

        val result = service.execute(studentIds)

        assertEquals(0, result.size)
        verify(repository).findAllByIds(studentIds)
        verify(enrollmentQueryPort).findEnrollmentWithClassroomByStudentIds(listOf(1))
        verify(gradeLookupPort, never()).findGradesByStudents(any())
    }

    @Test
    fun`deve retornar boletim com subjects vazio caso o aluno nao tenha notas`() {
        val studentIds = listOf(1)
        val student = Student(
            id = 1,
            name = "John",
            email = "john@example.com"
        )
        val enrollmentClassroom = EnrollmentClassroomDTO(
            enrollmentId = 1,
            active = true,
            studentId = 1,
            classroomId = 1,
            year = 2025,
            course = "Informática",
            grade = 3
        )

        whenever(repository.findAllByIds(studentIds))
            .thenReturn(listOf(student))

        whenever(
            enrollmentQueryPort.findEnrollmentWithClassroomByStudentIds(listOf(1))
        ).thenReturn(listOf(enrollmentClassroom))

        whenever(gradeLookupPort.findGradesByStudents(listOf(1)))
            .thenReturn(emptyMap())

        val result = service.execute(studentIds)
        val reportCard = result.first()

        assertEquals(1, result.size)

        assertEquals(student.name, reportCard.name)
        assertEquals(enrollmentClassroom.enrollmentId, reportCard.enrollmentId)
        assertEquals(enrollmentClassroom.course, reportCard.course)
        assertEquals(enrollmentClassroom.year, reportCard.year)
        assertEquals(enrollmentClassroom.grade, reportCard.grade)
        assertEquals(enrollmentClassroom.active, reportCard.active)

        assertEquals(0, reportCard.subjects.size)

        verify(repository).findAllByIds(studentIds)
        verify(enrollmentQueryPort).findEnrollmentWithClassroomByStudentIds(studentIds)
        verify(gradeLookupPort).findGradesByStudents(studentIds)
    }
}