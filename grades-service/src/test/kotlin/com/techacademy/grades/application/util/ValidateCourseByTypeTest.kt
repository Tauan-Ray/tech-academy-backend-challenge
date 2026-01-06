package com.techacademy.grades.application.util

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.service.exception.InvalidSubjectRuleException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ValidateCourseByTypeTest {

    @Test
    fun `deve permitir COURSE_SPECIFIC quando curso for informado`() {
        val dto = CreateSubjectDTO(
            name = "Banco de dados I",
            type = "COURSE_SPECIFIC",
            course = "Informática",
            grade = 1,
            workload = 180
        )

        assertDoesNotThrow {
            validateCourseByType(dto)
        }
    }

    @Test
    fun `deve lancar excecao quando COURSE_SPECIFIC nao possuir curso`() {
        val dto = CreateSubjectDTO(
            name = "Algoritmos",
            type = "COURSE_SPECIFIC",
            course = null,
            grade = 1,
            workload = 180

        )

        assertThrows(InvalidSubjectRuleException::class.java) {
            validateCourseByType(dto)
        }
    }

    @Test
    fun `deve permitir BASE quando curso nao for informado`() {
        val dto = CreateSubjectDTO(
            name = "Português",
            type = "BASE",
            course = null,
            grade = 1,
            workload = 180
        )

        assertDoesNotThrow {
            validateCourseByType(dto)
        }
    }

    @Test
    fun `deve lancar excecao quando BASE possuir curso informado`() {
        val dto = CreateSubjectDTO(
            name = "Matemática",
            type = "BASE",
            course = "Informática",
            grade = 1,
            workload = 180
        )

        assertThrows(InvalidSubjectRuleException::class.java) {
            validateCourseByType(dto)
        }
    }
}
