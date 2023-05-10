package com.roadmap.users.stream

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/streams")
class StreamController(private val streamService: StreamService) {

    @GetMapping
    fun getStream(): ResponseEntity<List<Stream>> {
        val streamsList = streamService.getStreams()
        return ResponseEntity.ok(streamsList)
    }

    @GetMapping("/{id}")
    fun getStreamById(@PathVariable id: String): ResponseEntity<Stream?> {
        val foundStream = streamService.getStreamById(id)
        return ResponseEntity.ok(foundStream)
    }

    @PostMapping
    fun createStream(@RequestBody stream: Stream): ResponseEntity<Stream> {
        val createdStream = streamService.createStream(stream)
        return ResponseEntity.created(URI.create("/${createdStream.id}")).body(createdStream)
    }

    @PutMapping("/{id}")
    fun updateStream(@PathVariable("id") id: String, @RequestBody stream: Stream): ResponseEntity<Stream> {
        val existingStream = streamService.getStreamById(id) ?: return ResponseEntity.notFound().build()
        existingStream.name = stream.name
        existingStream.shortName = stream.shortName
        existingStream.leadId = stream.leadId
        val updatedStream = streamService.updateStream(existingStream)
        return ResponseEntity.ok(updatedStream)
    }

    @DeleteMapping("/{id}")
    fun deleteStream(@PathVariable id: String): ResponseEntity<Unit> {
        streamService.deleteStream(id)
        return ResponseEntity.noContent().build()
    }
}