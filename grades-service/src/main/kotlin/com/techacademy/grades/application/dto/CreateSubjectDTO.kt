package com.techacademy.grades.application.dto

import com.techacademy.grades.domain.model.SubjectType

data class CreateSubjectDTO (
    val name: String,
    val grade: Int,
    val course: String? = null,
    val type: SubjectType,
    val workload: Int,
)