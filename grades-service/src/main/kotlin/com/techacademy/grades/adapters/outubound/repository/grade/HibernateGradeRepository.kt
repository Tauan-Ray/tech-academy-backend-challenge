package com.techacademy.grades.adapters.outubound.repository.grade

import com.techacademy.grades.adapters.outubound.entity.HibernateGradeEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HibernateGradeRepository: PanacheRepository<HibernateGradeEntity> {}