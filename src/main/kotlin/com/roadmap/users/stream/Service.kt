package com.roadmap.users.stream

import org.springframework.stereotype.Service

@Service
class StreamService(private val streamRepository: StreamRepository) {
    fun getStreams(): List<Stream> = streamRepository.findAllByIsActiveTrueOrderByOrder()

    fun createStream(user: Stream): Stream = streamRepository.save(user)

    fun getStreamById(id: String): Stream? = streamRepository.findByIsActiveTrueAndId(id)

    fun streamExistsById(id: String): Boolean = streamRepository.existsById(id)

    fun updateStream(user: Stream): Stream = streamRepository.save(user)
}