package com.techacademy.student.adapters.outbound.repository.student

import com.techacademy.student.adapters.outbound.entity.HibernateStudentEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateStudentRepository: PanacheRepository<HibernateStudentEntity> {}