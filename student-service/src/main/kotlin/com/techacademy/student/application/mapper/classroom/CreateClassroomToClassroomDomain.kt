package com.techacademy.student.application.mapper.classroom

import com.techacademy.student.application.dto.CreateClassroomDTO
import com.techacademy.student.domain.model.Classroom

fun CreateClassroomDTO.toDomain(): Classroom =
    Classroom(
        year = year,
        course = course,
        grade = grade,
    )