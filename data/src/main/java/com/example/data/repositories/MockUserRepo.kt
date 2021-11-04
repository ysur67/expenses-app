package com.example.data.repositories

import com.example.domain.UserRepository
import com.example.domain.models.Result
import com.example.domain.models.User
import kotlinx.coroutines.delay
import kotlin.coroutines.*

class MockUserRepo : UserRepository {
    override suspend fun Register(email: String, password: String): Result<User> {
        delay(5000L)
        return Result.Success(User(email, password))
    }

    override suspend fun Login(email: String, password: String): Result<User> {
        return Result.Success(User(email, password))
    }
}

