package com.techacademy.student.domain.repository

import com.techacademy.student.domain.model.Classroom
import com.techacademy.student.domain.model.Student

interface ClassroomRepositoryPort {
    fun findAll(): List<Classroom>
    fun findClassroom(id: Int): Classroom?
    fun findClassroomByIdentity(
        year: Int?,
        course: String?,
        grade: Int?
    ): List<Classroom>
    fun listStudentsOfClassroom(id: Int): List<Student>
    fun createClassroom(classroom: Classroom): Classroom
}