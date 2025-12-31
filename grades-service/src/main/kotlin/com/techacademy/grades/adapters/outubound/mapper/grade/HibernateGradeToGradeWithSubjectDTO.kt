package com.techacademy.grades.adapters.outubound.mapper.grade

import com.techacademy.grades.adapters.outubound.entity.HibernateGradeEntity
import com.techacademy.grades.application.dto.GradeWithSubjectDTO

fun HibernateGradeEntity.toGradeWithSubjectDTO(): GradeWithSubjectDTO =
    GradeWithSubjectDTO(
        enrollmentId = enrollmentId,
        subjectId = subject.id!!,
        subjectName = subject.name,
        grade = subject.grade,
        bimester = bimester,
        score = score
    )