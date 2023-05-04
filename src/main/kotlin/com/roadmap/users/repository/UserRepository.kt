package com.roadmap.users.repository

import com.roadmap.users.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    // тут можно добавлять свои методы для работы с пользователями, например:
    // fun findUserByStreamId(streamId: String): List<User>
}