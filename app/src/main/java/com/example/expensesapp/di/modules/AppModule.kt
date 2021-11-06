package com.example.expensesapp.di.modules

import com.example.data.repositories.FirebaseUserRepository
import com.example.domain.UserRepository
import com.example.domain.usecases.CheckIfUserIsAuthenticatedUseCase
import com.example.domain.usecases.LoginUserUseCase
import com.example.domain.usecases.RegisterUserUseCase
import com.example.expensesapp.domain.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fun provideUserRepository() : UserRepository {
        return FirebaseUserRepository(FirebaseAuth.getInstance())
    }
    single { provideUserRepository() }
    factory { RegisterUserUseCase(repo = get()) }
    factory { LoginUserUseCase(repo = get()) }
    factory { CheckIfUserIsAuthenticatedUseCase(repo = get()) }
    viewModel { UserViewModel(
        loginUseCase = get(),
        registerUseCase = get(),
        isAuthenticatedUseCase = get()
    ) }
}
