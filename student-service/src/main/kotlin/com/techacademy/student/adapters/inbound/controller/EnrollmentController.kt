package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.adapters.inbound.documentation.enrollment.*
import com.techacademy.student.application.dto.enrollment.CreateEnrollmentDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentDTO
import com.techacademy.student.application.dto.enrollment.EnrollmentWithDetailsDTO
import com.techacademy.student.application.usecase.enrollment.*
import jakarta.validation.Valid
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/enrollments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(
    name = "Enrollments",
    description = "Operações relacionadas ao gerenciamento matrículas"
)
class EnrollmentController(
    private val findAllEnrollmentsUseCase: FindAllEnrollmentsUseCase,
    private val findAllByStudentIdsUseCase: FindAllByStudentIdsUseCase,
    private val findEnrollmentByIdUseCase: FindEnrollmentByIdUseCase,
    private val findEnrollmentByIdWithDetailsUseCase: FindEnrollmentByIdWithDetailsUseCase,
    private val findEnrollmentByStudentUseCase: FindEnrollmentByStudentUseCase,
    private val findEnrollmentByClassroomUseCase: FindEnrollmentByClassroomUseCase,
    private val existsByStudentAndClassroomUseCase: ExistsByStudentAndClassroomUseCase,
    private val existsEnrollmentActiveByStudentUseCase: ExistsEnrollmentActiveByStudentUseCase,
    private val createEnrollmentUseCase: CreateEnrollmentUseCase,
) {
    @GET
    @Operation(
        summary = "Listar matrículas",
        description = "Retorna a lista de todas as matrículas cadastradas"
    )
    @FindAllEnrollmentsDoc
    fun findAll(): List<EnrollmentDTO> {
        return findAllEnrollmentsUseCase.execute()
    }

    @GET
    @Path("/students-by-ids")
    @Operation(
        summary = "Listar matrículas de uma lista de alunos",
        description = "Retorna a lista de todas as matrículas dos alunos buscados"
    )
    @Parameters(
        value = [
            Parameter(
                name = "studentIds",
                description = "Lista de IDs dos alunos separados por vírgula",
                example = "1,2,3",
                required = true
            )
        ]
    )
    @FindEnrollmentsByStudentIdsDoc
    fun findAllByStudentIds(
        @QueryParam("studentIds") studentIds: String?
    ): List<EnrollmentDTO> {

        if (studentIds.isNullOrBlank())
            throw BadRequestException("StudentIds é obrigatório!")

        val ids = try {
            studentIds.split(",").map { it.trim().toInt() }
        } catch (ex: NumberFormatException) {
            throw BadRequestException("studentIds deve ser uma lista de números inteiros!")
        }

        return findAllByStudentIdsUseCase.execute(ids)
    }



    @GET
    @Path("/{enrollmentId}")
    @Operation(
        summary = "Buscar matrícula por ID",
        description = "Retorna os dados de uma matrícula com base no ID informado. Caso não exista, retorna null."
    )
    @FindEnrollmentByIdDoc
    fun findEnrollmentById(
        @PathParam("enrollmentId") enrollmentId: Int
    ): EnrollmentDTO? {
        return findEnrollmentByIdUseCase.execute(enrollmentId)
    }



    @GET
    @Path("/{enrollmentId}/details")
    @Operation(
        summary = "Buscar matrícula por ID com detalhes",
        description = "Retorna os dados da matrícula com informações do aluno e da sala de aula. Caso não exista, retorna null."
    )
    @FindEnrollmentByIdWithDetailsDoc
    fun findEnrollmentByIdWithDetails(
        @PathParam("enrollmentId") enrollmentId: Int
    ): EnrollmentWithDetailsDTO? {
        return findEnrollmentByIdWithDetailsUseCase.execute(enrollmentId)
    }



    @GET
    @Path("/student/{studentId}")
    @Operation(
        summary = "Buscar matrículas por aluno",
        description = "Retorna todas as matrículas associadas a um aluno específico. Caso o aluno não exista, retorna erro."
    )
    @Parameters(
        value = [
            Parameter(
                name = "studentId",
                description = "ID do aluno",
                example = "1",
                required = true
            )
        ]
    )
    @FindEnrollmentsByStudentDoc
    fun findEnrollmentByStudent(
        @PathParam("studentId") studentId: Int
    ): List<EnrollmentDTO> {
        return findEnrollmentByStudentUseCase.execute(studentId)
    }


    @GET
    @Path("/classroom/{classroomId}")
    @Operation(
        summary = "Buscar matrículas por sala de aula",
        description = "Retorna todas as matrículas associadas a uma sala de aula. Caso a sala não exista, retorna erro."
    )
    @Parameters(
        value = [
            Parameter(
                name = "classroomId",
                description = "ID da sala de aula",
                example = "5",
                required = true
            )
        ]
    )
    @FindEnrollmentsByClassroomDoc
    fun findEnrollmentByClassroom(
        @PathParam("classroomId") classroomId: Int
    ): List<EnrollmentDTO> {
        return findEnrollmentByClassroomUseCase.execute(classroomId)
    }


    @GET
    @Path("/exists")
    @Operation(
        summary = "Verificar existência de matrícula",
        description = "Verifica se existe uma matrícula para um aluno em uma determinada sala de aula."
    )
    @Parameters(
        value = [
            Parameter(
                name = "studentId",
                description = "ID do aluno",
                example = "10",
                required = true
            ),
            Parameter(
                name = "classroomId",
                description = "ID da sala de aula",
                example = "3",
                required = true
            )
        ]
    )
    @ExistsEnrollmentDoc
    fun existsEnrollment(
        @QueryParam("studentId") studentId: Int,
        @QueryParam("classroomId") classroomId: Int
    ): Boolean {
        return existsByStudentAndClassroomUseCase
            .execute(studentId, classroomId)
    }


    @GET
    @Path("/active")
    @Operation(
        summary = "Verificar matrícula ativa por aluno",
        description = "Verifica se o aluno possui ao menos uma matrícula ativa."
    )
    @Parameters(
        value = [
            Parameter(
                name = "studentId",
                description = "ID do aluno",
                example = "12",
                required = true
            )
        ]
    )
    @ExistsEnrollmentActiveDoc
    fun existsEnrollmentActive(
        @QueryParam("studentId") studentId: Int,
    ): Boolean {
        return existsEnrollmentActiveByStudentUseCase
            .execute(studentId)
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar matrícula",
        description = "Cria uma matrícula vinculando um aluno a uma turma. " +
                "Um aluno não pode possuir mais de uma matrícula ativa."
    )
    @CreateEnrollmentDoc
    fun createEnrollment(
        @Valid createEnrollment: CreateEnrollmentDTO
    ): EnrollmentDTO {
        return createEnrollmentUseCase
            .execute(createEnrollment)
    }

}