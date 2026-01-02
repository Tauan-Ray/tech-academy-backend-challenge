package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentWithDetailsDTO

interface FindEnrollmentByIdWithDetailsUseCase {
    fun execute (enrollmentId: Int): EnrollmentWithDetailsDTO?
}