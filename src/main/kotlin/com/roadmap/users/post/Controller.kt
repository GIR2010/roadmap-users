package com.roadmap.users.post

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDateTime

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getPost(): ResponseEntity<List<PostModel>> {
        val postsList = postService.getPosts().map { i -> i.toPostModel()}
        return ResponseEntity.ok(postsList)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: String): ResponseEntity<Post?> {
        val foundPost = postService.getPostById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(foundPost)
    }

    @PostMapping
    fun createPost(@RequestBody post: PostModel): ResponseEntity<PostModel> {
        val newPost = post.toPost()
        newPost.createdAt = LocalDateTime.now()
        newPost.updatedAt = LocalDateTime.now()
        newPost.isActive = true
        val createdPost = postService.createPost(newPost)
        return ResponseEntity.created(URI.create("/${createdPost.id}")).body(createdPost.toPostModel())
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable("id") id: String, @RequestBody post: PostModel): ResponseEntity<PostModel> {
        val existingPost = postService.getPostById(id) ?: return ResponseEntity.notFound().build()
        existingPost.name = post.name
        existingPost.shortName = post.shortName
        existingPost.updatedAt = LocalDateTime.now()
        existingPost.order = post.order
        val updatedPost = postService.updatePost(existingPost)
        return ResponseEntity.ok(updatedPost.toPostModel())
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        val existingPost = postService.getPostById(id) ?: return ResponseEntity.notFound().build()
        existingPost.isActive = false
        existingPost.updatedAt = LocalDateTime.now()
        postService.updatePost(existingPost)
        return ResponseEntity.noContent().build()
    }
}