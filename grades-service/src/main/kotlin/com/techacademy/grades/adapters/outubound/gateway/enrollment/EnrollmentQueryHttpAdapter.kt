package com.techacademy.grades.adapters.outubound.gateway.enrollment

import com.techacademy.grades.application.dto.EnrollmentDetailsDTO
import com.techacademy.grades.application.port.EnrollmentQueryPort
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class EnrollmentQueryHttpAdapter(
    @param:RestClient
    private val enrollmentClient: EnrollmentClient
): EnrollmentQueryPort {

    override fun findEnrollmentById(enrollmentId: Int): EnrollmentDetailsDTO? {
        val enrollment = enrollmentClient.findEnrollmentByIdWithDetails(enrollmentId) ?: return null

        return EnrollmentDetailsDTO(
            id = enrollment.id,
            classroomId = enrollment.classroom.id,
            year = enrollment.classroom.year,
            course = enrollment.classroom.course,
            grade = enrollment.classroom.grade,
        )
    }
}