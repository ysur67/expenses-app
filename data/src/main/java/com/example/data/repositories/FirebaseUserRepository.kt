package com.example.data.repositories

import com.example.domain.UserRepository
import com.example.domain.models.Result
import com.example.domain.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseUserRepository(
    private val firebase: FirebaseAuth
) : UserRepository {
    override suspend fun Register(email: String, password: String): Result<User> {
        return try {
            val result = firebase.createUserWithEmailAndPassword(email, password).await()
            Result.Success(result.toModel())
        } catch (err: Exception) {
            Result.Error(err)
        }
    }

    override suspend fun Login(email: String, password: String): Result<User> {
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
