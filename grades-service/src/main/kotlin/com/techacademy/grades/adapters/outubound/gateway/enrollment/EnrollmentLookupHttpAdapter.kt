package com.techacademy.grades.adapters.outubound.gateway.enrollment

import com.techacademy.grades.domain.port.EnrollmentLookupPort
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class EnrollmentLookupHttpAdapter(
    @param:RestClient
    private val enrollmentClient: EnrollmentClient
): EnrollmentLookupPort {
    override fun findEnrollmentsByStudent(studentId: Int): List<Int> {
        val enrollmentIds = enrollmentClient
            .findEnrollmentsByStudent(studentId)
            .map { it.id }

        return enrollmentIds
    }

    override fun findEnrollmentsByStudents(studentIds: List<Int>): Map<Int, List<Int>> {
        if (studentIds.isEmpty()) return emptyMap()
        val paramRoute = studentIds.joinToString(",")

        val enrollments = enrollmentClient.findEnrollmentsByStudent(paramRoute)

        return enrollments
            .groupBy { it.studentId }
            .mapValues { (_, enrollments) ->
                enrollments.map { it.id }
            }
    }
}