package com.techacademy.student.adapters.outbound.repository.classroom

import com.techacademy.student.adapters.outbound.entity.HibernateClassroomEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateClassroomRepository: PanacheRepository<HibernateClassroomEntity> {}