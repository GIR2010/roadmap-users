package com.roadmap.users.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, String> {
}