package com.techacademy.grades.application.port

interface EnrollmentLookupPort {
    fun findEnrollmentsByStudent(studentId: Int): List<Int>
    fun findEnrollmentsByStudents(studentIds: List<Int>): Map<Int, List<Int>>
}