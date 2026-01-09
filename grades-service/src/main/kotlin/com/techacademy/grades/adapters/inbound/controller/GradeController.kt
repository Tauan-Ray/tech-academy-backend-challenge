package com.techacademy.grades.adapters.inbound.controller

import com.techacademy.grades.adapters.inbound.documentation.grade.CreateGradeDoc
import com.techacademy.grades.adapters.inbound.documentation.grade.FindAllGradesDoc
import com.techacademy.grades.adapters.inbound.documentation.grade.FindExistingGradesDoc
import com.techacademy.grades.adapters.inbound.documentation.grade.FindGradeByIdDoc
import com.techacademy.grades.adapters.inbound.documentation.grade.FindGradesByStudentDoc
import com.techacademy.grades.adapters.inbound.documentation.grade.FindGradesByStudentsDoc
import com.techacademy.grades.application.dto.CreateGradeDTO
import com.techacademy.grades.application.dto.GradeDTO
import com.techacademy.grades.application.dto.GradeWithSubjectDTO
import com.techacademy.grades.application.usecase.grade.CreateGradeUseCase
import com.techacademy.grades.application.usecase.grade.FindAllGradesUseCase
import com.techacademy.grades.application.usecase.grade.FindExistingGradesUseCase
import com.techacademy.grades.application.usecase.grade.FindGradeByStudentUseCase
import com.techacademy.grades.application.usecase.grade.FindGradeUseCase
import com.techacademy.grades.application.usecase.grade.FindGradesByStudentsUseCase
import com.techacademy.grades.domain.model.Bimester
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
import org.eclipse.microprofile.openapi.annotations.enums.Explode
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Path("/grades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(
    name = "Grades",
    description = "Operações relacionadas ao gerenciamento de notas"
)
class GradeController(
    private val findAllGradesUseCase: FindAllGradesUseCase,
    private val findGradeUseCase: FindGradeUseCase,
    private val findGradeByStudentUseCase: FindGradeByStudentUseCase,
    private val findGradesByStudentsUseCase: FindGradesByStudentsUseCase,
    private val findExistingGradesUseCase: FindExistingGradesUseCase,
    private val createGradeUseCase: CreateGradeUseCase,
) {
    @GET
    @Operation(
        summary = "Listar notas",
        description = "Retorna a lista de todas as notas cadastradas no sistema"
    )
    @FindAllGradesDoc
    fun findAll(): List<GradeDTO> {
        return findAllGradesUseCase.execute()
    }


    @GET
    @Path("/{id}")
    @Operation(
        summary = "Buscar nota por ID",
        description = "Retorna os dados de uma nota com base no ID informado. Caso não exista, retorna null."
    )
    @Parameters(
        value = [
            Parameter(
                name = "id",
                description = "Identificador da nota",
                example = "1",
                required = true
            )
        ]
    )
    @FindGradeByIdDoc
    fun findGrade(
        @PathParam("id") id: Int
    ): GradeDTO? {
        return findGradeUseCase.execute(id)
    }



    @GET
    @Path("/student/{id}")
    @Operation(
        summary = "Buscar notas por aluno",
        description = "Retorna todas as notas associadas a um aluno. Caso o aluno não exista, retorna erro."
    )
    @Parameters(
        value = [
            Parameter(
                name = "id",
                description = "Identificador do aluno",
                example = "10",
                required = true
            )
        ]
    )
    @FindGradesByStudentDoc
    fun findGradesByStudents(
        @PathParam("id") id: Int
    ): List<GradeDTO> {
        return findGradeByStudentUseCase.execute(id)
    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Buscar notas de múltiplos alunos",
        description = "Retorna as notas agrupadas por matrícula para uma lista de alunos. Caso nenhum aluno exista, retorna erro"
    )
    @Parameters(
        value = [
            Parameter(
                name = "studentIds",
                description = "Lista de IDs dos alunos separados por vírgula",
                example = "1,2,3",
                style = ParameterStyle.FORM,
                explode = Explode.FALSE
            )
        ]
    )
    @FindGradesByStudentsDoc
    fun findGradesByStudents(
        @QueryParam("studentIds") studentIds: String?
    ): Map<Int, List<GradeWithSubjectDTO>> {
        if (studentIds.isNullOrBlank()) throw BadRequestException("StudentIds é obrigatório!")

        val ids = try {
            studentIds.split(",").map { it.trim().toInt() }
        } catch (ex: NumberFormatException) {
            throw BadRequestException("studentIds deve ser uma lista de números inteiros!")
        }

        return findGradesByStudentsUseCase.execute(ids)
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Buscar notas existentes por filtros",
        description = "Busca notas existentes com base em filtros opcionais. Pelo menos UM dos filtros deve ser informado"
    )
    @Parameters(
        value = [
            Parameter(
                name = "enrollmentId",
                description = "Identificador da matrícula",
                example = "10",
                required = false
            ),
            Parameter(
                name = "subjectId",
                description = "Identificador da disciplina",
                example = "5",
                required = false
            ),
            Parameter(
                name = "bimester",
                description = "Bimestre da nota",
                example = "BIMESTER_1",
                required = false
            )
        ]
    )
    @FindExistingGradesDoc
    fun findClassroomByIdentity(
        @QueryParam("enrollmentId") enrollmentId: Int?,
        @QueryParam("subjectId") subjectId: Int?,
        @QueryParam("bimester") bimester: Bimester?
    ): List<GradeDTO> {
        return findExistingGradesUseCase.execute(enrollmentId, subjectId, bimester)
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar nota",
        description = "Cria uma nova nota para um aluno em uma disciplina e bimestre específicos."
    )
    @CreateGradeDoc
    fun createUser(@Valid createGrade: CreateGradeDTO): GradeDTO {
        return createGradeUseCase
            .execute(createGrade)
    }

}