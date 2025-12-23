package com.techacademy.grades.domain.repository

import com.techacademy.grades.domain.model.Subject

interface SubjectRepositoryPort {
    fun findAll(): List<Subject>
    fun findSubject(id: Int): Subject
    fun findSubjectByName(name: String): Subject?
}