package com.techacademy.grades.domain.port

interface ClassroomLookupPort {
    fun existsByIdentity(course: String?, grade: Int?): Boolean
}