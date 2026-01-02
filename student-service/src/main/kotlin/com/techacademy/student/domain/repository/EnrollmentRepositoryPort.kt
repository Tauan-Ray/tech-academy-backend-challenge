package com.techacademy.student.domain.repository

import com.techacademy.student.domain.model.Enrollment

interface EnrollmentRepositoryPort {
    fun findAll(): List<Enrollment>
    fun findAllByStudentIds(studentIds: List<Int>): List<Enrollment>
    fun findById(enrollmentId: Int): Enrollment?
    fun findByStudent(studentId: Int): List<Enrollment>
    fun findByClassroom(classroomId: Int): List<Enrollment>
    fun createEnrollment(enrollment: Enrollment): Enrollment
    fun existsByStudentAndClassroom(
        studentId: Int,
        classroomId: Int
    ): Boolean
    fun existsActiveByStudent(studentId: Int): Boolean
}