package com.roadmap.users.post


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {
    fun getPosts(): List<Post> = postRepository.findAll()

    fun createPost(user: Post): Post = postRepository.save(user)

    fun getPostById(id: String): Post? = postRepository.findByIdOrNull(id)

    fun updatePost(user: Post): Post = postRepository.save(user)

    fun deletePost(id: String) = postRepository.deleteById(id)
}