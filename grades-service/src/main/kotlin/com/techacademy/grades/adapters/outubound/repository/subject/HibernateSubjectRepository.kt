package com.techacademy.grades.adapters.outubound.repository.subject

import com.techacademy.grades.adapters.outubound.entity.HibernateSubjectEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateSubjectRepository: PanacheRepository<HibernateSubjectEntity> {}