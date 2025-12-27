package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO

interface FindEnrollmentByStudentUseCase {
    fun execute(studentId: Int): List<EnrollmentDTO>
}