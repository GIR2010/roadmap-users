package com.roadmap.users.repository

import com.roadmap.users.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    // тут можно добавлять свои методы для работы с пользователями, например:
    // fun findUserByStreamId(streamId: String): List<User>
}