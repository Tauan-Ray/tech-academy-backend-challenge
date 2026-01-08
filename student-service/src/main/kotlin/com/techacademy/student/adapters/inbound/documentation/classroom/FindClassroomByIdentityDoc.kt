package com.techacademy.student.adapters.inbound.documentation.classroom

import com.techacademy.student.application.dto.classroom.ClassroomDTO
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@APIResponse(
    responseCode = "200",
    description = "Lista de salas de aula encontrada com base nos filtros fornecidos",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = ClassroomDTO::class,
                type = SchemaType.ARRAY
            )
        )
    ]
)
@APIResponse(
    responseCode = "400",
    description = "Nenhum filtro informado (pelo menos year, course ou grade deve ser fornecido)"
)
annotation class FindClassroomByIdentityDoc
