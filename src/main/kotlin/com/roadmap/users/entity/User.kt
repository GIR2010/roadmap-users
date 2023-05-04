package com.roadmap.users.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
class User(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String? = null,
    val surname: String? = null,
    @Column(name= "second_name")
    val secondName: String? = null,
    @Column(name= "stream_id")
    val streamId: String? = null,
    @Column(name= "post_id")
    val postId: String? = null,
    val avatar: String? = null
)