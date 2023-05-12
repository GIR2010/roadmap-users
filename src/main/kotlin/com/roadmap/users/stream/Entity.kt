package com.roadmap.users.stream

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
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
    var leadId: String? = null,

    @Column(name= "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name= "order_id")
    var order: Int = 0,

    @Column(name = "is_active")
    var isActive: Boolean = true
)

data class StreamModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String = "unnamed",
    var shortName: String? = null,
    var leadId: String? = null,
    val order: Int = 0
)

fun Stream.toStreamModel() = StreamModel(
    id = id,
    name = name,
    shortName = shortName,
    leadId = leadId,
    order = order
)

fun StreamModel.toStream() = Stream(
    id = id,
    name = name,
    shortName = shortName,
    leadId = leadId,
    order = order,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now(),
    isActive = true
)