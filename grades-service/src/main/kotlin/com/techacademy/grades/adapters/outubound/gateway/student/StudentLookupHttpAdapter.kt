package com.techacademy.grades.adapters.outubound.gateway.student

import com.techacademy.grades.domain.port.StudentLookupPort
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class StudentLookupHttpAdapter(
    @param:RestClient
    private val studentClient: StudentClient
): StudentLookupPort {

    override fun existsById(studentId: Int): Boolean {
        val student = studentClient.exists(studentId)

        return student != null
    }

    override fun findExistingIds(studentIds: List<Int>): List<Int> {
        val paramRoute = studentIds.joinToString(",")

        return studentClient.findExistingIds(paramRoute)
            .map { it.id }
    }
}