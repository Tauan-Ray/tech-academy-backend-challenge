package com.techacademy.grades.application.service.grade

import com.techacademy.grades.application.dto.GradeWithSubjectDTO
import com.techacademy.grades.application.port.EnrollmentLookupPort
import com.techacademy.grades.application.port.GradeQueryPort
import com.techacademy.grades.application.usecase.grade.FindGradesByStudentsUseCase
import com.techacademy.grades.domain.port.StudentLookupPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindGradesByStudentsService(
    private val gradeQueryPort: GradeQueryPort,
    private val studentLookupPort: StudentLookupPort,
    private val enrollmentLookupPort: EnrollmentLookupPort,
): FindGradesByStudentsUseCase {

    override fun execute(studentIds: List<Int>): Map<Int, List<GradeWithSubjectDTO>> {
        val existingStudentsIds = studentLookupPort.findExistingIds(studentIds)
        if (existingStudentsIds.isEmpty()) return emptyMap()

        val enrollmentIdsByStudent = enrollmentLookupPort
            .findEnrollmentsByStudents(existingStudentsIds)

        val allEnrollmentsIds = enrollmentIdsByStudent.values.flatten()
        if (allEnrollmentsIds.isEmpty()) return emptyMap()

        val grades = gradeQueryPort.findGradesWithSubjectsByEnrollmentIds(allEnrollmentsIds)

        val gradesByEnrollmentId = grades.groupBy { it.enrollmentId }

        return gradesByEnrollmentId
    }
}