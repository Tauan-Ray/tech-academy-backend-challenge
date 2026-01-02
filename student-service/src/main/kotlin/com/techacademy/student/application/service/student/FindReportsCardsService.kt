package com.techacademy.student.application.service.student

import com.techacademy.student.application.dto.student.ReportCardDTO
import com.techacademy.student.application.mapper.student.toSubjectReportCard
import com.techacademy.student.application.port.EnrollmentQueryPort
import com.techacademy.student.application.usecase.student.FindReportsCardsUseCase
import com.techacademy.student.domain.port.GradeLookupPort
import com.techacademy.student.domain.repository.StudentRepositoryPort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FindReportsCardsService(
    private val studentRepository: StudentRepositoryPort,
    private val enrollmentQueryPort: EnrollmentQueryPort,
    private val gradeLookupPort: GradeLookupPort,
): FindReportsCardsUseCase {

    override fun execute(studentIds: List<Int>): List<ReportCardDTO> {
        val students = studentRepository.findAllByIds(studentIds)
        if (students.isEmpty()) return emptyList()

        val studentIdsFound = students.map { it.id!! }

        val enrollmentsByStudent = enrollmentQueryPort
            .findEnrollmentWithClassroomByStudentIds(studentIdsFound)
            .groupBy { it.studentId }

        val gradesByEnrollmentId =
            gradeLookupPort.findGradesByStudents(studentIdsFound)

        return students.flatMap { student ->
            val studentEnrollments = enrollmentsByStudent[student.id].orEmpty()

            studentEnrollments.map { enrollment ->
                ReportCardDTO(
                    enrollmentId = enrollment.enrollmentId,
                    name = student.name,
                    year = enrollment.year,
                    course = enrollment.course,
                    grade = enrollment.grade,
                    subjects = gradesByEnrollmentId[enrollment.enrollmentId]
                        .orEmpty()
                        .map { it.toSubjectReportCard() }
                )
            }
        }
    }
}