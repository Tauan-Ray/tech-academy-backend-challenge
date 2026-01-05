package com.techacademy.student.application.service.classroom

import com.techacademy.student.application.service.exception.MissingClassroomFilterException
import com.techacademy.student.domain.model.Classroom
import com.techacademy.student.domain.repository.ClassroomRepositoryPort
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.whenever

class FindClassroomByIdentityServiceTest {
    private val repository: ClassroomRepositoryPort = mock()
    private val service = FindClassroomByIdentityService(repository)

    @Test
    fun`deve lancar excecao caso nao seja enviado nenhuma parametro de busca`() {
        assertThrows(MissingClassroomFilterException::class.java) {
            service.execute(null, null, null)
        }

        verify(repository,
            never()).findClassroomByIdentity(any(), any(), any())
    }

    @Test
    fun `deve retornar lista de ClassroomDTO quando filtros forem informados`() {
        val classroom1 = Classroom(1, 2026, "Matemática", 1)
        val classroom2 = Classroom(2, 2026, "Ciências", 2)
        val classrooms = listOf(classroom1, classroom2)

        whenever(repository.findClassroomByIdentity(2026, "Matemática", 1))
            .thenReturn(listOf(classroom1))

        whenever(repository.findClassroomByIdentity(2026, "Ciências", 2))
            .thenReturn(listOf(classroom2))

        val result1 = service.execute(2026, "Matemática", 1)
        val result2 = service.execute(2026, "Ciências", 2)

        verify(repository).findClassroomByIdentity(2026, "Matemática", 1)
        verify(repository).findClassroomByIdentity(2026, "Ciências", 2)

        assertEquals(1, result1.size)
        assertEquals(classroom1.id, result1[0].id)
        assertEquals(classroom1.year, result1[0].year)
        assertEquals(classroom1.course, result1[0].course)
        assertEquals(classroom1.grade, result1[0].grade)

        assertEquals(1, result2.size)
        assertEquals(classroom2.id, result2[0].id)
        assertEquals(classroom2.year, result2[0].year)
        assertEquals(classroom2.course, result2[0].course)
        assertEquals(classroom2.grade, result2[0].grade)
    }
}