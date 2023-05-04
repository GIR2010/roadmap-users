package com.roadmap.users.post


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(private val userRepository: PostRepository) {
    fun getPosts(): List<Post> = userRepository.findAll()

    fun createPost(user: Post): Post = userRepository.save(user)

    fun getPostById(id: String): Post? = userRepository.findByIdOrNull(id)

    fun updatePost(user: Post): Post = userRepository.save(user)

    fun deletePost(id: String) = userRepository.deleteById(id)
}