package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.service.exception.MissingGradeFilterException
import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.model.Grade
import com.techacademy.grades.domain.repository.GradeRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class FindExistingGradesServiceTest {

    private val gradeRepository: GradeRepositoryPort = mock()
    private val service = FindExistingGradesService(gradeRepository)

    @Test
    fun `deve lancar excecao quando nenhum filtro for informado`() {
        assertThrows(MissingGradeFilterException::class.java) {
            service.execute(
                enrollmentId = null,
                subjectId = null,
                bimester = null
            )
        }

        verify(gradeRepository, never())
            .findExistingGrades(any(), any(), any())
    }

    @Test
    fun `deve buscar notas quando ao menos um filtro for informado`() {
        val enrollmentId = 1

        whenever(
            gradeRepository.findExistingGrades(
                enrollmentId,
                null,
                null
            )
        ).thenReturn(emptyList())

        val result = service.execute(
            enrollmentId = enrollmentId,
            subjectId = null,
            bimester = null
        )

        assertEquals(0, result.size)

        verify(gradeRepository)
            .findExistingGrades(enrollmentId, null, null)
    }

    @Test
    fun `deve retornar lista de GradeDTO corretamente`() {
        val grade = Grade(
            id = 1,
            enrollmentId = 1,
            subjectId = 1,
            bimester = Bimester.BIMESTER_1,
            score = BigDecimal(8.5)
        )

        whenever(
            gradeRepository.findExistingGrades(
                enrollmentId = 1,
                subjectId = null,
                bimester = Bimester.BIMESTER_1
            )
        ).thenReturn(listOf(grade))

        val result = service.execute(
            enrollmentId = 1,
            subjectId = null,
            bimester = Bimester.BIMESTER_1
        )

        assertEquals(1, result.size)
        assertEquals(BigDecimal(8.5), result.first().score)
        assertEquals(Bimester.BIMESTER_1, result.first().bimester)

        verify(gradeRepository)
            .findExistingGrades(1, null, Bimester.BIMESTER_1)
    }
}
