package com.roadmap.users.stream

import org.springframework.data.jpa.repository.JpaRepository

interface StreamRepository : JpaRepository<Stream, String> {
    fun findAllByIsActiveTrueOrderByOrder(): List<Stream>
    fun findByIsActiveTrueAndId(id: String): Stream?
}