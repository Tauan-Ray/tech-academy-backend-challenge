package com.techacademy.grades.application.port

import com.techacademy.grades.application.dto.GradeWithSubjectDTO

interface GradeQueryPort {
    fun findGradesWithSubjectsByEnrollmentIds(
        enrollmentIds: List<Int>,
    ): List<GradeWithSubjectDTO>
}