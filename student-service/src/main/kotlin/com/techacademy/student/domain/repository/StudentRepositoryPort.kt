package com.techacademy.student.domain.repository

import com.techacademy.student.domain.model.Student

interface StudentRepositoryPort {
    fun findAll(): List<Student>
    fun findStudent(id: Int): Student?
    fun findStudentByEmail(email: String): Student?
    fun createStudent(student: Student): Student
}