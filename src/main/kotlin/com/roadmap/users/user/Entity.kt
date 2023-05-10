package com.roadmap.users.user

import com.roadmap.users.post.Post
import com.roadmap.users.stream.Stream
import jakarta.persistence.*
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
    @ManyToOne(cascade = [CascadeType.REMOVE])
    var stream: Stream? = null,
    @ManyToOne(cascade = [CascadeType.REMOVE])
    var post: Post? = null,
    var avatar: String? = null
)

data class UserModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String? = null,
    var surname: String? = null,
    var secondName: String? = null,
    var streamId: String? = null,
    var postId: String? = null,
    var avatar: String? = null
)