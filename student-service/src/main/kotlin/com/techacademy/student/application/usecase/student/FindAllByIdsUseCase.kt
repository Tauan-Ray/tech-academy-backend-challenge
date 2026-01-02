package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.student.StudentDTO

interface FindAllByIdsUseCase {
    fun execute (studentIds: List<Int>): List<StudentDTO>
}