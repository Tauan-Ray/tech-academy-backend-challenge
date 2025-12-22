package com.techacademy.student.application.mapper.classroom

import com.techacademy.student.application.dto.ClassroomDTO
import com.techacademy.student.domain.model.Classroom

fun Classroom.toDTO(): ClassroomDTO =
    ClassroomDTO(
        id = id,
        year = year,
        course = course,
        grade = grade,
        createdAt = createdAt,
    )