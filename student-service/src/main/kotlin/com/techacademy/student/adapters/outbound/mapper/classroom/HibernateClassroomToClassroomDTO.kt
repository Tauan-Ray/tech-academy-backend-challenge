package com.techacademy.student.adapters.outbound.mapper.classroom

import com.techacademy.student.adapters.outbound.entity.HibernateClassroomEntity
import com.techacademy.student.application.dto.classroom.ClassroomDTO

fun HibernateClassroomEntity.toDTO(): ClassroomDTO =
    ClassroomDTO(
        id = id,
        year = year,
        course = course,
        grade = grade,
        createdAt = createdAt
    )