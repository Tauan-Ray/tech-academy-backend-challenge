package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO

interface FindAllEnrollmentsUseCase {
    fun execute(): List<EnrollmentDTO>
}