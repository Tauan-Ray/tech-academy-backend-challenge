package com.techacademy.grades.adapters.outubound.mapper.grade

import com.techacademy.grades.adapters.outubound.entity.HibernateGradeEntity
import com.techacademy.grades.adapters.outubound.entity.HibernateSubjectEntity
import com.techacademy.grades.domain.model.Grade

fun Grade.toEntity(subject: HibernateSubjectEntity): HibernateGradeEntity =
    HibernateGradeEntity().apply {
        id = this@toEntity.id
        enrollmentId = this@toEntity.enrollmentId
        this.subject = subject
        score = this@toEntity.score
        bimester = this@toEntity.bimester

        createdAt = requireNotNull(this@toEntity.createdAt)
        updatedAt = requireNotNull(this@toEntity.updatedAt)
        deletedAt = this@toEntity.deletedAt
    }