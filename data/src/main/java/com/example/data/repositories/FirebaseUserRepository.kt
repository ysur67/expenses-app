package com.example.data.repositories

import com.example.domain.UserRepository
import com.example.domain.models.Result
import com.example.domain.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseUserRepository : UserRepository {
    private val firebase: FirebaseAuth = FirebaseAuth.getInstance()

    override val isAuthenticated: Boolean
        get() = firebase.currentUser != null

    override suspend fun register(email: String, password: String): Result<User> {
        return try {
            val result = firebase.createUserWithEmailAndPassword(email, password).await()
            Result.Success(result.toModel())
        } catch (err: Exception) {
            Result.Error(err)
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val result = firebase.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.toModel())
        } catch (err: Exception) {
            Result.Error(err)
        }
    }
}

fun AuthResult.toModel() : User {
    return User(
        email = user?.email!!
    )
}
