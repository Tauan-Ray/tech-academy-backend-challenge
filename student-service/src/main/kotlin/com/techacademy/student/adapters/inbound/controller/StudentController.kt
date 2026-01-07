package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.adapters.inbound.documentation.student.CreateStudentDoc
import com.techacademy.student.adapters.inbound.documentation.student.DeleteStudentDoc
import com.techacademy.student.adapters.inbound.documentation.student.FindAllStudentsDoc
import com.techacademy.student.adapters.inbound.documentation.student.FindStudentByEmailDoc
import com.techacademy.student.adapters.inbound.documentation.student.FindStudentByIdDoc
import com.techacademy.student.adapters.inbound.documentation.student.FindStudentsByIdsDoc
import com.techacademy.student.adapters.inbound.documentation.student.GenerateReportCardDoc
import com.techacademy.student.adapters.inbound.documentation.student.UpdateStudentDoc
import com.techacademy.student.application.dto.student.CreateStudentDTO
import com.techacademy.student.application.dto.student.ReportCardDTO
import com.techacademy.student.application.dto.student.StudentDTO
import com.techacademy.student.application.dto.student.UpdateStudentDTO
import com.techacademy.student.application.usecase.student.CreateStudentUseCase
import com.techacademy.student.application.usecase.student.DeleteStudentUseCase
import com.techacademy.student.application.usecase.student.FindAllByIdsUseCase
import com.techacademy.student.application.usecase.student.FindAllStudentsUseCase
import com.techacademy.student.application.usecase.student.FindReportsCardsUseCase
import com.techacademy.student.application.usecase.student.FindStudentByEmailUseCase
import com.techacademy.student.application.usecase.student.FindStudentUseCase
import com.techacademy.student.application.usecase.student.UpdateStudentUseCase
import jakarta.validation.Valid
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse


@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(
    name = "Students",
    description = "Operações relacionadas ao gerenciamento de alunos"
)
class StudentController(
    private val findAllStudentsUseCase: FindAllStudentsUseCase,
    private val findAllByIdsUseCase: FindAllByIdsUseCase,
    private val findStudentUseCase: FindStudentUseCase,
    private val findStudentByEmailUseCase: FindStudentByEmailUseCase,
    private val findReportsCardsUseCase: FindReportsCardsUseCase,
    private val createStudentUseCase: CreateStudentUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
) {
    @GET
    @Operation(
        summary = "Listar alunos",
        description = "Retorna a lista de todos os alunos cadastrados no sistema"
    )
    @FindAllStudentsDoc
    fun findAll(): List<StudentDTO> {
        return findAllStudentsUseCase.execute()
    }

    @GET
    @Path("/by-ids")
    @Operation(
        summary = "Buscar alunos por IDs",
        description = "Retorna uma lista de alunos com base em uma lista de IDs informada via query param"
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
    @FindStudentsByIdsDoc
    fun findAllByIds(@QueryParam("studentIds") studentIds: String?): List<StudentDTO> {
        if (studentIds.isNullOrBlank()) throw BadRequestException("StudentId é obrigatório!")

        val ids = try {
            studentIds.split(",").map { it.trim().toInt() }
        } catch (ex: NumberFormatException) {
            throw BadRequestException("StudentIds deve ser uma lista de números inteiros!")
        }

        return findAllByIdsUseCase
            .execute(studentIds = ids)
    }

    @GET
    @Path("/report-card")
    @Operation(
        summary = "Gerar boletim dos alunos",
        description = "Gera o boletim escolar dos alunos com base em uma lista de IDs informada via query param"
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
    @GenerateReportCardDoc
    fun generateReportCard(
        @QueryParam("studentIds") studentIds: String?
    ): List<ReportCardDTO> {

        if (studentIds.isNullOrBlank())
            throw BadRequestException("StudentId é obrigatório!")

        val ids = try {
            studentIds.split(",").map { it.trim().toInt() }
        } catch (ex: NumberFormatException) {
            throw BadRequestException("StudentIds deve ser uma lista de números inteiros!")
        }

        return findReportsCardsUseCase.execute(studentIds = ids)
    }

    @GET
    @Path("/{id}")
    @Operation(
        summary = "Buscar aluno por ID",
        description = "Retorna os dados de um aluno com base no ID informado. Caso o aluno não exista, retorna null."
    )
    @FindStudentByIdDoc
    fun findStudent(
        @PathParam("id") id: Int
    ): StudentDTO? {
        return findStudentUseCase.execute(id)
    }


    @GET
    @Path("/email")
    @Operation(
        summary = "Buscar aluno por email",
        description = "Retorna os dados de um aluno com base no email informado. Caso o aluno não exista, retorna null."
    )
    @Parameters(
        value = [
            Parameter(
                name = "email",
                description = "Email do aluno",
                example = "joao.silva@email.com",
                required = true
            )
        ]
    )
    @FindStudentByEmailDoc
    fun findStudentByEmail(
        @QueryParam("email") email: String
    ): StudentDTO? {
        return findStudentByEmailUseCase.execute(email)
    }


    @POST
    @Operation(
        summary = "Criar aluno",
        description = "Cria um novo aluno no sistema"
    )
    @CreateStudentDoc
    fun createUser(
        @Valid createStudent: CreateStudentDTO
    ): StudentDTO {
        return createStudentUseCase.execute(createStudent)
    }


    @PATCH
    @Path("/{id}")
    @Operation(
        summary = "Atualizar aluno",
        description = "Atualiza parcialmente os dados de um aluno existente"
    )
    @UpdateStudentDoc
    fun updateUser(
        @PathParam("id") id: Int,
        @Valid updateStudent: UpdateStudentDTO
    ): StudentDTO {
        return updateStudentUseCase.execute(id, updateStudent)
    }


    @DELETE
    @Path("/{id}")
    @Operation(
        summary = "Remover aluno",
        description = "Remove um aluno existente com base no ID informado"
    )
    @DeleteStudentDoc
    fun deleteUser(
        @PathParam("id") id: Int
    ) {
        deleteStudentUseCase.execute(id)
    }

}