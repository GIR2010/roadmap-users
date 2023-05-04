package com.roadmap.users.service

import com.roadmap.users.entity.User
import com.roadmap.users.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUsers(): List<User> = userRepository.findAll()

    fun createUser(user: User): User = userRepository.save(user)

    fun getUserById(id: String): User? = userRepository.findByIdOrNull(id)

    fun updateUser(user: User): User = userRepository.save(user)

    fun deleteUser(id: String) = userRepository.deleteById(id)

//    fun getUserById(id: String?): User {
//        return userRepository.findById(id) //.orElse(null)
//    }
//
//    fun updateUser(user: User?): User {
//        return userRepository.save(user)
//    }
//
//    fun deleteUserById(id: String?) {
//        userRepository.deleteById(id)
//    }
}