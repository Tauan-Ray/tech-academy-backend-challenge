package com.techacademy.student.application.port

import com.techacademy.student.application.dto.enrollment.EnrollmentClassroomDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentWithDetailsDTO

interface EnrollmentQueryPort {
    fun findEnrollmentWithClassroomByStudentIds(
        studentIds: List<Int>,
    ): List<EnrollmentClassroomDTO>
    fun findByIdWithDetails(enrollmentId: Int): EnrollmentWithDetailsDTO?
}