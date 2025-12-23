package com.techacademy.grades.adapters.outubound.repository

import com.techacademy.grades.adapters.outubound.entity.HibernateSubjectEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateSubjectRepository: PanacheRepository<HibernateSubjectEntity> {}