package com.roadmap.users.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
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
    var isLead: Boolean = false
)