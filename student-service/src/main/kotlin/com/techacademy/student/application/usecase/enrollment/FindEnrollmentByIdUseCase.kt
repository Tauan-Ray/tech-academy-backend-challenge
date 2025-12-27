package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO

interface FindEnrollmentByIdUseCase {
    fun execute(enrollmentId: Int): EnrollmentDTO?
}