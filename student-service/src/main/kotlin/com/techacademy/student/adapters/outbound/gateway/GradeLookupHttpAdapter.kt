package com.techacademy.student.adapters.outbound.gateway

import com.techacademy.student.application.dto.GradeResponseDTO
import com.techacademy.student.domain.port.GradeLookupPort
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class GradeLookupHttpAdapter(
    @param:RestClient
    private val gradeClient: GradeClient
): GradeLookupPort {
    override fun findGradesByStudents(studentIds: List<Int>): Map<Int, List<GradeResponseDTO>> {
        val paramRoute = studentIds.joinToString(",")

        return gradeClient.findGradesByStudents(paramRoute)
    }
}