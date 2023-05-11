package com.roadmap.users.user

import com.roadmap.users.post.Post
import com.roadmap.users.stream.Stream
import jakarta.persistence.*
import java.time.LocalDateTime
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

    var avatar: String? = null,

    @Column(name= "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name= "order_id")
    var order: Int = 0,

    @Column(name = "is_active")
    var isActive: Boolean = true
)

data class UserModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String? = null,
    var surname: String? = null,
    var secondName: String? = null,
    var streamId: String? = null,
    var postId: String? = null,
    var avatar: String? = null,
    val order: Int = 0
)

fun User.toUserModel() = UserModel(
    id = id,
    name = name,
    surname = surname,
    secondName = secondName,
    streamId = stream?.id,
    postId = post?.id,
    avatar = avatar,
    order = order
)

fun UserModel.toUser() = User(
    id = id,
    name = name,
    surname = surname,
    secondName = secondName,
    stream = if (streamId != null) Stream(id= streamId!!) else null,
    post = if (postId != null) Post(id= postId!!) else null,
    avatar = avatar,
    order = order,
    isActive = true,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)