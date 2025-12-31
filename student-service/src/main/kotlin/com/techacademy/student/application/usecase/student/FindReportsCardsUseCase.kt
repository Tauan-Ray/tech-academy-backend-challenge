package com.techacademy.student.application.usecase.student

import com.techacademy.student.application.dto.ReportCardDTO

interface FindReportsCardsUseCase {
    fun execute(studentIds: List<Int>): List<ReportCardDTO>
}