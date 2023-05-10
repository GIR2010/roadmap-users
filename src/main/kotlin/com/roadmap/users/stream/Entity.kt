package com.roadmap.users.stream

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "streams")
class Stream(
    @Id
    val id: String = UUID.randomUUID().toString(),
    var name: String = "unnamed",
    @Column(name= "short_name")
    var shortName: String? = null,
    @Column(name= "lead_id")
    var leadId: String? = null
)