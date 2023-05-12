package com.roadmap.users.stream

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDateTime

@RestController
@RequestMapping("/streams")
class StreamController(private val streamService: StreamService) {

    @GetMapping
    fun getStream(): ResponseEntity<List<StreamModel>> {
        val streamsList = streamService.getStreams().map {s -> s.toStreamModel()}
        return ResponseEntity.ok(streamsList)
    }

    @GetMapping("/{id}")
    fun getStreamById(@PathVariable id: String): ResponseEntity<StreamModel?> {
        val foundStream = streamService.getStreamById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(foundStream.toStreamModel())
    }

    @PostMapping
    fun createStream(@RequestBody stream: StreamModel): ResponseEntity<StreamModel> {
        val newStream = stream.toStream()
        newStream.createdAt = LocalDateTime.now()
        newStream.updatedAt = LocalDateTime.now()
        newStream.isActive = true
        val createdStream = streamService.createStream(newStream)
        return ResponseEntity.created(URI.create("/${createdStream.id}")).body(createdStream.toStreamModel())
    }

    @PutMapping("/{id}")
    fun updateStream(@PathVariable("id") id: String, @RequestBody stream: StreamModel): ResponseEntity<StreamModel> {
        val existingStream = streamService.getStreamById(id) ?: return ResponseEntity.notFound().build()
        existingStream.name = stream.name
        existingStream.shortName = stream.shortName
        existingStream.leadId = stream.leadId
        existingStream.updatedAt = LocalDateTime.now()
        existingStream.order = stream.order
        val updatedStream = streamService.updateStream(existingStream)
        return ResponseEntity.ok(updatedStream.toStreamModel())
    }

    @DeleteMapping("/{id}")
    fun deleteStream(@PathVariable id: String): ResponseEntity<Unit> {
        val existingStream = streamService.getStreamById(id) ?: return ResponseEntity.notFound().build()
        existingStream.isActive = false
        existingStream.updatedAt = LocalDateTime.now()
        streamService.updateStream(existingStream)
        return ResponseEntity.noContent().build()
    }
}