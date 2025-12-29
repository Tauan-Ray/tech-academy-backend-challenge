package com.techacademy.grades.domain.port

interface EnrollmentLookupPort {
    fun findEnrollmentsByStudent(studentId: Int): List<Int>
}