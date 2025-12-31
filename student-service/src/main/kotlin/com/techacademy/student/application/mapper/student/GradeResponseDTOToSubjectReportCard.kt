package com.techacademy.student.application.mapper.student

import com.techacademy.student.application.dto.GradeResponseDTO
import com.techacademy.student.application.dto.SubjectReportCardDTO

fun GradeResponseDTO.toSubjectReportCard(): SubjectReportCardDTO =
    SubjectReportCardDTO(
        name = subjectName,
        bimester = bimester,
        score = score,
        grade  = grade
    )