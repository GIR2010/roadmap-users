package com.roadmap.users.repository

import com.roadmap.users.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, String> {
}