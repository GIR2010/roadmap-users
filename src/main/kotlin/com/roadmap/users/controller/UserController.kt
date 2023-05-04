package com.roadmap.users.controller

import com.roadmap.users.entity.User
import com.roadmap.users.entity.UserModel
import com.roadmap.users.service.UserService
import com.roadmap.users.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService, private val postService: PostService) {

    @GetMapping
    fun getUser(): ResponseEntity<List<User>> {
        val usersList = userService.getUsers()
        return ResponseEntity.ok(usersList)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<User?> {
        val foundUser = userService.getUserById(id)
        return ResponseEntity.ok(foundUser)
    }

    @PostMapping
    fun createUser(@RequestBody user: UserModel): ResponseEntity<User> {
        val post = if (user.postId != null) postService.getPostById(user.postId!!) else null
        val userModel = User(
            name = user.name,
            surname = user.surname,
            secondName = user.secondName,
            avatar = user.avatar,
            streamId = user.streamId,
            post = post
        )
        val createdUser = userService.createUser(userModel)
        return ResponseEntity.created(URI.create("/${createdUser.id}")).body(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable("id") id: String, @RequestBody user: UserModel): ResponseEntity<User> {
        val existingUser = userService.getUserById(id) ?: return ResponseEntity.notFound().build()
        val post = if (user.postId != null) postService.getPostById(user.postId!!) else null
        existingUser.name = user.name
        existingUser.surname = user.surname
        existingUser.secondName = user.secondName
        existingUser.streamId = user.streamId
        existingUser.post = post
        existingUser.avatar = user.avatar
        val updatedUser = userService.updateUser(existingUser)
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}
