package com.roadmap.users.stream

import org.springframework.data.jpa.repository.JpaRepository

interface StreamRepository : JpaRepository<Stream, String> {
}