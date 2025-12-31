package com.techacademy.student.application.port

import com.techacademy.student.application.dto.EnrollmentClassroomDTO


interface EnrollmentQueryPort {
    fun findEnrollmentWithClassroomByStudentIds(
        studentIds: List<Int>,
    ): List<EnrollmentClassroomDTO>
}