package com.example.domain

import com.example.domain.models.User
import com.example.domain.params.LoginUserParam
import com.example.domain.params.RegisterUserParam

interface UserRepository {
    suspend fun Register(email: String, password: String) : Result<User>
    suspend fun Login(email: String, password: String) : Result<User>
}
