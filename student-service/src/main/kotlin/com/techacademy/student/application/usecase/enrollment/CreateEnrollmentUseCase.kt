package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.CreateEnrollmentDTO
import com.techacademy.student.application.dto.EnrollmentDTO

interface CreateEnrollmentUseCase {
    fun execute(createEnrollment: CreateEnrollmentDTO): EnrollmentDTO
}