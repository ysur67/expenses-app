package com.example.domain.usecases

import com.example.domain.UserRepository
import com.example.domain.models.Result
import com.example.domain.models.User
import com.example.domain.params.LoginUserParam

class CheckIfUserIsAuthenticatedUseCase(
    private val repo: UserRepository
) {
    fun execute() : Boolean {
        return repo.isAuthenticated
    }
}
