package com.techacademy.grades.adapters.outubound.entity

import com.techacademy.grades.domain.model.Bimester
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "grades")
class HibernateGradeEntity: PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade", nullable = false)
    var id: Int? = null

    @Column(name = "student_id", nullable = false)
    var studentId: Int = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    lateinit var subject: HibernateSubjectEntity

    @Column(name = "score", precision = 4, scale = 2, nullable = false)
    lateinit var score: BigDecimal

    @Enumerated(EnumType.STRING)
    @Column(name = "bimester", nullable = false, length = 15)
    lateinit var bimester: Bimester

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