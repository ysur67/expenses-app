package com.example.domain.usecases

import com.example.domain.UserRepository
import com.example.domain.models.Result
import com.example.domain.models.User
import com.example.domain.params.LoginUserParam

class LoginUserUseCase(private val repo: UserRepository) {
    suspend fun execute(params: LoginUserParam) : Result<User> {
        return repo.Login(params.username, params.password)
    }
}