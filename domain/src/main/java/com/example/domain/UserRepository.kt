package com.example.domain

import com.example.domain.models.User
import com.example.domain.models.Result

interface UserRepository {
    suspend fun Register(email: String, password: String) : Result<User>
    suspend fun Login(email: String, password: String) : Result<User>
}
