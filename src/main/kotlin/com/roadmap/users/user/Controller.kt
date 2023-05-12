package com.roadmap.users.user

import com.roadmap.users.post.PostService
import com.roadmap.users.stream.StreamService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.time.LocalDateTime

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val postService: PostService,
    private val streamService: StreamService,
) {

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserModel>> {
        val usersList = userService.getUsers().map { u -> u.toUserModel() }
        return ResponseEntity.ok(usersList)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<UserModel?> {
        val foundUser = userService.getUserById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(foundUser.toUserModel())
    }

    @PostMapping
    fun createUser(@RequestBody user: UserModel): ResponseEntity<UserModel> {
        val postExists = if (user.postId != null) postService.postExistsById(user.postId!!) else false
        if (user.postId != null && !postExists) throw IllegalArgumentException("Not valid postId")
        val streamExists = if (user.streamId != null) streamService.streamExistsById(user.streamId!!) else false
        if (user.streamId != null && !streamExists) throw IllegalArgumentException("Not valid streamId")

        val newUser = user.toUser()

        val createdUser = userService.createUser(newUser)
        return ResponseEntity.created(URI.create("/${createdUser.id}")).body(createdUser.toUserModel())
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable("id") id: String, @RequestBody user: UserModel): ResponseEntity<UserModel> {
        val existingUser = userService.getUserById(id) ?: return ResponseEntity.notFound().build()
        val post = if (user.postId != null) postService.getPostById(user.postId!!) else null
        if (user.postId != null && post == null) throw IllegalArgumentException("Not valid postId")
        val stream = if (user.streamId != null) streamService.getStreamById(user.streamId!!) else null
        if (user.streamId != null && stream == null) throw IllegalArgumentException("Not valid streamId")

        existingUser.name = user.name
        existingUser.surname = user.surname
        existingUser.secondName = user.secondName
        existingUser.stream = stream
        existingUser.post = post
        existingUser.avatar = user.avatar
        existingUser.updatedAt = LocalDateTime.now()
        existingUser.isActive = true
        existingUser.order = user.order

        val updatedUser = userService.updateUser(existingUser)
        return ResponseEntity.ok(updatedUser.toUserModel())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Unit> {
        val existingUser = userService.getUserById(id) ?: return ResponseEntity.notFound().build()
        existingUser.isActive = false

        userService.updateUser(existingUser)
        return ResponseEntity.noContent().build()
    }
}
