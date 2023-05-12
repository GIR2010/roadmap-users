package com.roadmap.users.post

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "posts")
class Post(
    @Id
    val id: String = UUID.randomUUID().toString(),

    var name: String = "unnamed",

    @Column(name= "short_name")
    var shortName: String? = null,

    @Column(name= "is_lead")
    var isLead: Boolean = false,

    @Column(name= "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name= "order_id")
    var order: Int = 0,

    @Column(name = "is_active")
    var isActive: Boolean = true
)

data class PostModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String = "unnamed",
    var shortName: String? = null,
    val order: Int = 0
)

fun Post.toPostModel() = PostModel(
    id = id,
    name = name,
    shortName = shortName,
    order = order
)

fun PostModel.toPost() = Post(
    id = id,
    name = name,
    shortName = shortName,
    isLead = false,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now(),
    order = order
)