package com.roadmap.users.controller

import com.roadmap.users.entity.Post
import com.roadmap.users.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getPost(): ResponseEntity<List<Post>> {
        val postsList = postService.getPosts()
        return ResponseEntity.ok(postsList)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: String): ResponseEntity<Post?> {
        val foundPost = postService.getPostById(id)
        return ResponseEntity.ok(foundPost)
    }

    @PostMapping
    fun createPost(@RequestBody post: Post): ResponseEntity<Post> {
        val createdPost = postService.createPost(post)
        return ResponseEntity.created(URI.create("/${createdPost.id}")).body(createdPost)
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable("id") id: String, @RequestBody post: Post): ResponseEntity<Post> {
        val existingPost = postService.getPostById(id) ?: return ResponseEntity.notFound().build()
        existingPost.name = post.name
        existingPost.shortName = post.shortName
        existingPost.isLead = post.isLead
        val updatedPost = postService.updatePost(existingPost)
        return ResponseEntity.ok(updatedPost)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        postService.deletePost(id)
        return ResponseEntity.noContent().build()
    }
}