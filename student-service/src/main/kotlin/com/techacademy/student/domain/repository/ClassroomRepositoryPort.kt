package com.techacademy.student.domain.repository

import com.techacademy.student.domain.model.Classroom

interface ClassroomRepositoryPort {
    fun findAll(): List<Classroom>
    fun findClassroom(id: Int): Classroom?
    fun findClassroomByIdentity(
        year: Int,
        course: String,
        grade: Int
    ): Classroom?
}