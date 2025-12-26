package com.techacademy.grades.domain.repository

import com.techacademy.grades.domain.model.Grade


interface GradeRepositoryPort {
    fun findAll(): List<Grade>
    fun findGrade(id: Int): Grade?
    fun findGradeByStudent(id: Int): Grade?
    fun createGrade(grade: Grade): Grade
}