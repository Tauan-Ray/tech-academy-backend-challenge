package com.techacademy.student.application.usecase.enrollment

import com.techacademy.student.application.dto.EnrollmentDTO

interface FindEnrollmentByClassroomUseCase {
    fun execute(classroomId: Int): List<EnrollmentDTO>
}