package com.techacademy.student.application.usecase.enrollment

interface ExistsByStudentAndClassroomUseCase {
    fun execute(studentId: Int, classroomId: Int): Boolean
}