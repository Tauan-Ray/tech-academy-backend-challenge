package com.techacademy.student.adapters.outbound.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "classrooms")
class HibernateClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom", nullable = false)
    var id: Int? = null

    @Column(name = "year", nullable = false)
    var year: Int = 0

    @Column(name = "course", nullable = false, length = 100)
    lateinit var course: String

    @Column(name = "grade", nullable = false)
    var grade: Int = 0

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    var students: List<HibernateStudentEntity> = mutableListOf()

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