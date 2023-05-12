package com.roadmap.users.post


import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {
    fun getPosts(): List<Post> = postRepository.findAllByIsActiveTrueOrderByOrder()

    fun createPost(user: Post): Post = postRepository.save(user)

    fun getPostById(id: String): Post? = postRepository.findByIsActiveTrueAndId(id)

    fun postExistsById(id: String): Boolean = postRepository.existsById(id)

    fun updatePost(user: Post): Post = postRepository.save(user)
}