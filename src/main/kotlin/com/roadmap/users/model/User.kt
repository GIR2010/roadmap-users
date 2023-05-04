package com.roadmap.users.model

import lombok.Data
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Data
data class User(
    @NotNull
    val id: String = UUID.randomUUID().toString(),

    @NotBlank(message = "Name is mandatory")
    val name: String,

    @NotBlank(message = "Surname is mandatory")
    val surname: String,

    val secondName: String? = null,

    val streamId: String? = null,

    val postId: String? = null,

    val avatar: String? = null
)