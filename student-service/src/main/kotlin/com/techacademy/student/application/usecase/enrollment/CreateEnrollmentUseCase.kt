package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.enrollment.CreateEnrollmentDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentDTO

interface CreateEnrollmentUseCase {
    fun execute(createEnrollment: CreateEnrollmentDTO): EnrollmentDTO
}