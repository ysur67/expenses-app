package com.example.domain.usecases

import com.example.domain.UserRepository
import com.example.domain.models.Result
import com.example.domain.models.User
import com.example.domain.params.RegisterUserParam

class RegisterUserUseCase(private val repo: UserRepository) {
    suspend fun execute(params: RegisterUserParam) : Result<User> {
        return repo.register(params.email, params.password)
    }
}