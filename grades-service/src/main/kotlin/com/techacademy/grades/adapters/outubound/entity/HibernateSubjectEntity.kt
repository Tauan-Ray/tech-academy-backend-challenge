package com.techacademy.grades.adapters.outubound.entity

import com.techacademy.grades.domain.model.SubjectType
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "subjects")
class HibernateSubjectEntity: PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject", nullable = false)
    var id: Int? = null

    @Column(name = "name", length = 50, nullable = false)
    lateinit var name: String

    @Column(name = "grade", nullable = false)
    var grade: Int = 0

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "subject_type")
    lateinit var type: SubjectType

    @Column(name = "course", nullable = true, length = 50)
    var course: String? = null

    @Column(name = "workload", nullable = false)
    var workload: Int = 0

    @Column(nullable = false, updatable = false, name = "created_at")
    lateinit var createdAt: LocalDateTime

    @Column(nullable = false, name = "updated_at")
    lateinit var updatedAt: LocalDateTime

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null


    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}