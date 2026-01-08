package com.techacademy.grades.adapters.inbound.controller

import com.techacademy.grades.adapters.inbound.documentation.subject.CreateSubjectDoc
import com.techacademy.grades.adapters.inbound.documentation.subject.FindAllSubjectsDoc
import com.techacademy.grades.adapters.inbound.documentation.subject.FindSubjectByIdDoc
import com.techacademy.grades.adapters.inbound.documentation.subject.FindSubjectByNameDoc
import com.techacademy.grades.application.dto.CreateSubjectDTO
import com.techacademy.grades.application.dto.SubjectDTO
import com.techacademy.grades.application.usecase.subject.CreateSubjectUseCase
import com.techacademy.grades.application.usecase.subject.FindAllSubjectsUseCase
import com.techacademy.grades.application.usecase.subject.FindSubjectByNameUseCase
import com.techacademy.grades.application.usecase.subject.FindSubjectUseCase
import jakarta.validation.Valid
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

@Path("/subjects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(
    name = "Subjects",
    description = "Operações relacionadas ao gerenciamento de disciplinas"
)
class SubjectController(
    private val findAllSubjectsUseCase: FindAllSubjectsUseCase,
    private val findSubjectUseCase: FindSubjectUseCase,
    private val findSubjectByNameUseCase: FindSubjectByNameUseCase,
    private val createSubjectUseCase: CreateSubjectUseCase,
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar disciplinas",
        description = "Retorna a lista de todas as disciplinas cadastradas no sistema"
    )
    @FindAllSubjectsDoc
    fun findAll(): List<SubjectDTO> {
        return findAllSubjectsUseCase.execute()
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Buscar disciplina por ID",
        description = "Retorna os dados de uma disciplina com base no ID informado. Caso não exista, retorna null."
    )
    @FindSubjectByIdDoc
    fun findSubject(
        @PathParam("id") id: Int
    ): SubjectDTO? {
        return findSubjectUseCase.execute(id)
    }


    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Buscar disciplina por nome",
        description = "Retorna os dados de uma disciplina com base no nome informado. Caso não exista, retorna null."
    )
    @Parameters(
        value = [
            Parameter(
                name = "name",
                description = "Nome da disciplina",
                example = "Matemática",
                required = true
            )
        ]
    )
    @FindSubjectByNameDoc
    fun findSubjectByName(
        @QueryParam("name") name: String
    ): SubjectDTO? {
        return findSubjectByNameUseCase.execute(name)
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar disciplina",
        description = "Cria uma nova disciplina no sistema"
    )
    @CreateSubjectDoc
    fun createUser(
        @Valid createSubject: CreateSubjectDTO
    ): SubjectDTO {
        return createSubjectUseCase.execute(createSubject)
    }
}