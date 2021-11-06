package com.example.domain

import com.example.domain.models.User
import com.example.domain.models.Result

interface UserRepository {
    val isAuthenticated: Boolean
    suspend fun register(email: String, password: String) : Result<User>
    suspend fun login(email: String, password: String) : Result<User>
}
