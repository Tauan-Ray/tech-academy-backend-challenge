package com.techacademy.grades.application.util

import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.service.exception.InvalidSubjectRuleException

fun validateCourseByType(createSubject: CreateSubjectDTO) {
    when (createSubject.type) {
        "COURSE_SPECIFIC" -> {
            if (createSubject.course.isNullOrBlank()) {
                throw InvalidSubjectRuleException(
                    "Para disciplinas COURSE_SPECIFIC, o curso é obrigatório"
                )
            }
        }

        "BASE" -> {
            if (!createSubject.course.isNullOrBlank()) {
                throw InvalidSubjectRuleException(
                    "Disciplinas BASE não devem possuir curso"
                )
            }
        }
    }
}
