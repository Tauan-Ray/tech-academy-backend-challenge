package com.techacademy.grades.application.grade

import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.application.dto.EnrollmentDetailsDTO
import com.techacademy.grades.application.port.EnrollmentQueryPort
import com.techacademy.grades.application.service.exception.EnrollmentNotExistsException
import com.techacademy.grades.application.service.exception.GradeAlreadyExistsException
import com.techacademy.grades.application.service.exception.InvalidEnrollmentToGradeException
import com.techacademy.grades.application.service.exception.SubjectNotExistsException
import com.techacademy.grades.application.service.grade.CreateGradeService
import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.model.Grade
import com.techacademy.grades.domain.model.Subject
import com.techacademy.grades.domain.model.SubjectType
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import com.techacademy.grades.domain.repository.SubjectRepositoryPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class CreateGradeServiceTest {
    private val gradeRepository: GradeRepositoryPort = mock()
    private val subjectRepository: SubjectRepositoryPort = mock()
    private val enrollmentQueryPort: EnrollmentQueryPort = mock()
    private val service = CreateGradeService(
        gradeRepository,
        subjectRepository,
        enrollmentQueryPort
    )

    @Test
    fun`deve lancar excecao se matricula nao existir`() {
        val dto = CreateGradeDTO(
            enrollmentId = 1,
            subjectId = 1,
            bimester = "BIMESTER_1",
            score = BigDecimal(7.8)
        )

        whenever(enrollmentQueryPort.findEnrollmentById(dto.enrollmentId))
            .thenReturn(null)

        assertThrows(EnrollmentNotExistsException::class.java) {
            service.execute(dto)
        }

        verify(enrollmentQueryPort).findEnrollmentById(dto.enrollmentId)
        verify(subjectRepository, never()).findSubject(any())
        verify(gradeRepository, never()).findExistingGrades(any(),any(),any())
        verify(gradeRepository, never()).createGrade(any())
    }

    @Test
    fun`deve lancar excecao se disciplina nao existir`() {
        val dto = CreateGradeDTO(
            enrollmentId = 1,
            subjectId = 1,
            bimester = "BIMESTER_1",
            score = BigDecimal(7.8)
        )

        whenever(enrollmentQueryPort.findEnrollmentById(dto.enrollmentId))
            .thenReturn(mock())

        whenever(subjectRepository.findSubject(dto.subjectId))
            .thenReturn(null)

        assertThrows(SubjectNotExistsException::class.java) {
            service.execute(dto)
        }

        verify(enrollmentQueryPort).findEnrollmentById(dto.enrollmentId)
        verify(subjectRepository).findSubject(dto.subjectId)
        verify(gradeRepository, never()).findExistingGrades(any(),any(),any())
        verify(gradeRepository, never()).createGrade(any())
    }

    @Test
    fun `deve lancar excecao quando disciplina nao pertence ao curso ou serie da matricula`() {
        val dto = CreateGradeDTO(
            enrollmentId = 1,
            subjectId = 1,
            bimester = "BIMESTER_1",
            score = BigDecimal(8.0)
        )

        val enrollment = EnrollmentDetailsDTO(
            id = 1,
            classroomId = 10,
            year = 2024,
            course = "Informática",
            grade = 2,
            active = true
        )

        val subject = Subject(
            id = 1,
            name = "Fundamentos de Administração",
            course = "Administração",
            grade = 2,
            type = SubjectType.COURSE_SPECIFIC,
            workload = 120,
        )

        whenever(enrollmentQueryPort.findEnrollmentById(dto.enrollmentId))
            .thenReturn(enrollment)

        whenever(subjectRepository.findSubject(dto.subjectId))
            .thenReturn(subject)

        assertThrows(InvalidEnrollmentToGradeException::class.java) {
            service.execute(dto)
        }

        verify(enrollmentQueryPort).findEnrollmentById(dto.enrollmentId)
        verify(subjectRepository).findSubject(dto.subjectId)
        verify(gradeRepository, never()).findExistingGrades(any(), any(), any())
        verify(gradeRepository, never()).createGrade(any())
    }

    @Test
    fun `deve lancar excecao quando ja existir nota para a matricula disciplina e bimestre`() {
        val dto = CreateGradeDTO(
            enrollmentId = 1,
            subjectId = 1,
            bimester = "BIMESTER_1",
            score = BigDecimal(9.0)
        )

        val enrollment = EnrollmentDetailsDTO(
            id = 1,
            classroomId = 10,
            year = 2024,
            course = "Informática",
            grade = 2,
            active = true
        )

        val subject = Subject(
            id = 1,
            name = "Banco de Dados I",
            course = "Informática",
            grade = 2,
            type = SubjectType.COURSE_SPECIFIC,
            workload = 120,
        )

        whenever(enrollmentQueryPort.findEnrollmentById(dto.enrollmentId))
            .thenReturn(enrollment)

        whenever(subjectRepository.findSubject(dto.subjectId))
            .thenReturn(subject)

        whenever(
            gradeRepository.findExistingGrades(
                dto.enrollmentId,
                dto.subjectId,
                Bimester.BIMESTER_1
            )
        ).thenReturn(listOf(mock()))

        assertThrows(GradeAlreadyExistsException::class.java) {
            service.execute(dto)
        }

        verify(enrollmentQueryPort).findEnrollmentById(dto.enrollmentId)
        verify(subjectRepository).findSubject(dto.subjectId)
        verify(gradeRepository).findExistingGrades(
            dto.enrollmentId,
            dto.subjectId,
            Bimester.BIMESTER_1
        )
        verify(gradeRepository, never()).createGrade(any())
    }

    @Test
    fun `deve criar nota com sucesso quando todos os dados forem validos`() {
        val dto = CreateGradeDTO(
            enrollmentId = 1,
            subjectId = 1,
            bimester = "BIMESTER_1",
            score = BigDecimal(8.5)
        )

        val enrollment = EnrollmentDetailsDTO(
            id = 1,
            classroomId = 10,
            year = 2024,
            course = "Informática",
            grade = 2,
            active = true
        )

        val subject = Subject(
            id = 1,
            name = "Banco de Dados I",
            course = "Informática",
            grade = 2,
            type = SubjectType.COURSE_SPECIFIC,
            workload = 120,
        )

        whenever(enrollmentQueryPort.findEnrollmentById(dto.enrollmentId))
            .thenReturn(enrollment)

        whenever(subjectRepository.findSubject(dto.subjectId))
            .thenReturn(subject)

        whenever(
            gradeRepository.findExistingGrades(
                dto.enrollmentId,
                dto.subjectId,
                Bimester.BIMESTER_1
            )
        ).thenReturn(emptyList())

        whenever(gradeRepository.createGrade(any()))
            .thenAnswer { it.arguments[0] as Grade }

        val gradeCaptor = argumentCaptor<Grade>()

        val result = service.execute(dto)

        verify(enrollmentQueryPort).findEnrollmentById(dto.enrollmentId)
        verify(subjectRepository).findSubject(dto.subjectId)
        verify(gradeRepository).findExistingGrades(
            dto.enrollmentId,
            dto.subjectId,
            Bimester.BIMESTER_1
        )
        verify(gradeRepository).createGrade(gradeCaptor.capture())

        val savedGrade = gradeCaptor.firstValue

        assertEquals(dto.enrollmentId, savedGrade.enrollmentId)
        assertEquals(dto.subjectId, savedGrade.subjectId)
        assertEquals(Bimester.BIMESTER_1, savedGrade.bimester)
        assertEquals(dto.score, savedGrade.score)
        assertEquals(savedGrade.createdAt, savedGrade.updatedAt)
    }

}