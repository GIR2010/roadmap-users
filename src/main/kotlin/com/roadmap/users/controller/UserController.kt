package com.roadmap.users.controller

import com.roadmap.users.entity.User
import com.roadmap.users.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

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
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
        return ResponseEntity.ok(createdUser)
    }
}
