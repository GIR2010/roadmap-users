package com.roadmap.users.stream

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StreamService(private val streamRepository: StreamRepository) {
    fun getStreams(): List<Stream> = streamRepository.findAll()

    fun createStream(user: Stream): Stream = streamRepository.save(user)

    fun getStreamById(id: String): Stream? = streamRepository.findByIdOrNull(id)

    fun updateStream(user: Stream): Stream = streamRepository.save(user)

    fun deleteStream(id: String) = streamRepository.deleteById(id)
}