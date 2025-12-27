package com.techacademy.student.adapters.outbound.repository.enrollment

import com.techacademy.student.adapters.outbound.entity.HibernateEnrollmentEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateEnrollmentRepository: PanacheRepository<HibernateEnrollmentEntity> {}