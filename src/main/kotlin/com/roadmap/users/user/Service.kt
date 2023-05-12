package com.roadmap.users.user

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUsers(): List<User> = userRepository.findAllByIsActiveTrueOrderByOrder()

    fun createUser(user: User): User = userRepository.save(user)

    fun getUserById(id: String): User? = userRepository.findByIsActiveTrueAndId(id)

    fun updateUser(user: User): User = userRepository.save(user)
}