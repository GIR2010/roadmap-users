package com.roadmap.users.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findAllByIsActiveTrueOrderByOrder(): List<User>
    fun findByIsActiveTrueAndId(id: String): User?
}