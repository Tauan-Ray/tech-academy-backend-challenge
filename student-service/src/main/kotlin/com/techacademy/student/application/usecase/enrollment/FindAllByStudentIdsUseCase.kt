package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.enrollment.EnrollmentDTO

interface FindAllByStudentIdsUseCase {
    fun execute(studentIds: List<Int>): List<EnrollmentDTO>
}