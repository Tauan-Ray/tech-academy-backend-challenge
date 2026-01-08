package com.techacademy.student.adapters.inbound.controller

import com.techacademy.student.adapters.inbound.documentation.classroom.CreateClassroomDoc
import com.techacademy.student.adapters.inbound.documentation.classroom.FindAllClassroomsDoc
import com.techacademy.student.adapters.inbound.documentation.classroom.FindClassroomByIdDoc
import com.techacademy.student.adapters.inbound.documentation.classroom.FindClassroomByIdentityDoc
import com.techacademy.student.adapters.inbound.documentation.classroom.ListStudentsOfClassroomDoc
import com.techacademy.student.application.dto.classroom.ClassroomDTO
import com.techacademy.student.application.dto.classroom.CreateClassroomDTO
import com.techacademy.student.application.dto.student.StudentDTO
import com.techacademy.student.application.usecase.classroom.CreateClassroomUseCase
import com.techacademy.student.application.usecase.classroom.FindAllClassroomsUseCase
import com.techacademy.student.application.usecase.classroom.FindClassroomByIdentityUseCase
import com.techacademy.student.application.usecase.classroom.FindClassroomUseCase
import com.techacademy.student.application.usecase.classroom.ListStudentsOfClassroomUseCase
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

@Path("classrooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(
    name = "Classrooms",
    description = "Operações relacionadas ao gerenciamento de salas de aula"
)
class ClassroomController(
    private val findAllClassroomsUseCase: FindAllClassroomsUseCase,
    private val findClassroomUseCase: FindClassroomUseCase,
    private val findClassroomByIdentityUseCase: FindClassroomByIdentityUseCase,
    private val listStudentsOfClassroomUseCase: ListStudentsOfClassroomUseCase,
    private val createClassroomUseCase: CreateClassroomUseCase,
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar salas de aula",
        description = "Retorna a lista de todas as salas de aula cadastradas"
    )
    @FindAllClassroomsDoc
    fun findAll(): List<ClassroomDTO> {
        return findAllClassroomsUseCase.execute()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Buscar sala de aula por ID",
        description = "Retorna os detalhes de uma sala de aula com base no ID informado, ou null se não existir"
    )
    @Parameter(
        name = "id",
        description = "ID da sala de aula",
        required = true,
        example = "1"
    )
    @FindClassroomByIdDoc
    fun findClassroom(@PathParam("id") id: Int): ClassroomDTO? {
        return findClassroomUseCase.execute(id)
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Buscar salas de aula por identidade",
        description = "Retorna uma lista de salas de aula filtradas por ano, curso e/ou série. Pelo menos um filtro deve ser informado."
    )
    @Parameters(
        value = [
            Parameter(
                name = "year",
                description = "Ano da sala de aula (opcional)",
                example = "2026",
                required = false
            ),
            Parameter(
                name = "course",
                description = "Curso da sala de aula (opcional)",
                example = "Informática",
                required = false
            ),
            Parameter(
                name = "grade",
                description = "Série da sala de aula (opcional)",
                example = "3",
                required = false
            )
        ]
    )
    @FindClassroomByIdentityDoc
    fun findClassroomByIdentity(
        @QueryParam("year") year: Int?,
        @QueryParam("course") course: String?,
        @QueryParam("grade") grade: Int?
    ): List<ClassroomDTO> {
        return findClassroomByIdentityUseCase.execute(year, course, grade)
    }


    @GET
    @Path("/{id}/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar alunos de uma sala de aula",
        description = "Retorna a lista de alunos pertencentes à sala de aula especificada pelo ID"
    )
    @ListStudentsOfClassroomDoc
    fun listStudentsOfClassroom(@PathParam("id") id: Int): List<StudentDTO> {
        return listStudentsOfClassroomUseCase.execute(id)
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar sala de aula",
        description = "Cria uma nova sala de aula no sistema com ano, curso e série"
    )
    @CreateClassroomDoc
    fun createClassroom(
        @Valid createClassroom: CreateClassroomDTO
    ): ClassroomDTO {
        return createClassroomUseCase.execute(createClassroom)
    }

}