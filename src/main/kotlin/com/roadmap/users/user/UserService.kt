package com.roadmap.users.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUsers(): List<User> = userRepository.findAll()

    fun createUser(user: User): User = userRepository.save(user)

    fun getUserById(id: String): User? = userRepository.findByIdOrNull(id)

    fun updateUser(user: User): User = userRepository.save(user)

    fun deleteUser(id: String) = userRepository.deleteById(id)
}