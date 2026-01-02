package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO

interface FindEnrollmentByIdUseCase {
    fun execute(enrollmentId: Int): EnrollmentDTO?
}