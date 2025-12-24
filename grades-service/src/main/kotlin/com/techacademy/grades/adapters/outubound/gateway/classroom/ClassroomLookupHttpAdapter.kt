package com.techacademy.grades.adapters.outubound.gateway.classroom

import com.techacademy.grades.domain.port.ClassroomLookupPort
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class ClassroomLookupHttpAdapter(
    @param:RestClient
    private val classroomClient: ClassroomClient,
): ClassroomLookupPort {

    override fun existsByIdentity(course: String?, grade: Int?): Boolean {
        return classroomClient
            .search(course, grade)
            .isNotEmpty()
    }
}