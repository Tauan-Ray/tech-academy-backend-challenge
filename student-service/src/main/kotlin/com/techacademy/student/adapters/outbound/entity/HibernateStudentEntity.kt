package com.techacademy.student.adapters.outbound.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity()
@Table(name = "students")
class HibernateStudentEntity: PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student", nullable = false)
    var id: Int? = null

    @Column(name = "name", nullable = false, length = 60)
    lateinit var name: String

    @Column(name = "email", nullable = false, unique = true, length = 255)
    lateinit var email: String

    @Column(nullable = false, updatable = false, name = "created_at")
    lateinit var createdAt: LocalDateTime

    @Column(nullable = false, name = "updated_at")
    lateinit var updatedAt: LocalDateTime

    @Column(name = "deleted_at")
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
