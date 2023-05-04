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
    var name: String? = null,
    var surname: String? = null,
    @Column(name= "second_name")
    var secondName: String? = null,
    @Column(name= "stream_id")
    var streamId: String? = null,
    @Column(name= "post_id")
    var postId: String? = null,
    var avatar: String? = null
)