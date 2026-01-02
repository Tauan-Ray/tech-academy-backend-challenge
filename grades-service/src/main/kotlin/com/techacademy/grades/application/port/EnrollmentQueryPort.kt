package com.techacademy.grades.application.port

import com.techacademy.grades.application.dto.EnrollmentDetailsDTO

interface EnrollmentQueryPort {
    fun findEnrollmentById(enrollmentId: Int): EnrollmentDetailsDTO?
}