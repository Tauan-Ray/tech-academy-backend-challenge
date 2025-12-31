package com.techacademy.student.domain.port

import com.techacademy.student.application.dto.GradeResponseDTO

interface GradeLookupPort {
    fun findGradesByStudents(studentIds: List<Int>): Map<Int, List<GradeResponseDTO>>
}