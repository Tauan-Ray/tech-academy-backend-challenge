package com.techacademy.grades.domain.repository

import com.techacademy.grades.domain.model.Bimester
import com.techacademy.grades.domain.model.Grade


interface GradeRepositoryPort {
    fun findAll(): List<Grade>
    fun findGrade(id: Int): Grade?
    fun findGradeByEnrollmentIds(enrollmentIds: List<Int>): List<Grade>
    fun findExistingGrades(
        enrollmentId: Int?,
        subjectId: Int?,
        bimester: Bimester?,
    ): List<Grade>
    fun createGrade(grade: Grade): Grade
}