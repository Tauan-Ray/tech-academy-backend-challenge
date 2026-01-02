package com.techacademy.student.application.usecase.enrollment

interface ExistsEnrollmentActiveByStudentUseCase {
    fun execute(studentId: Int): Boolean
}