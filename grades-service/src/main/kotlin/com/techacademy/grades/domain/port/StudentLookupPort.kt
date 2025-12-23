package com.techacademy.grades.domain.port

interface StudentLookupPort {
    fun existsById(studentId: Int): Boolean
}