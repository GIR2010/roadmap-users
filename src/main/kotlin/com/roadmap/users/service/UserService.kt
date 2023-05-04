package com.roadmap.users.service

import com.roadmap.users.model.User
import com.roadmap.users.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(user: User?): User {
        return userRepository.save(user)
    }

    fun getUserById(id: String?): User {
        return userRepository.findById(id) //.orElse(null)
    }

    fun updateUser(user: User?): User {
        return userRepository.save(user)
    }

    fun deleteUserById(id: String?) {
        userRepository.deleteById(id)
    }
}