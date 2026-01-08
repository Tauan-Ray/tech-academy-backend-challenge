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
    description = "Sala de aula encontrada ou null se não existir",
    content = [
        Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = Schema(
                implementation = ClassroomDTO::class,
                type = SchemaType.OBJECT,
                nullable = true
            )
        )
    ]
)
annotation class FindClassroomByIdDoc
