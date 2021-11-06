package com.example.expensesapp.di.modules

import com.example.data.repositories.FirebaseExpenseRepository
import com.example.data.repositories.FirebaseUserRepository
import com.example.domain.ExpenseRepository
import com.example.domain.UserRepository
import com.example.domain.usecases.*
import com.example.expensesapp.domain.ExpenseViewModel
import com.example.expensesapp.domain.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fun provideUserRepository() : UserRepository {
        return FirebaseUserRepository()
    }
    fun provideExpenseRepository() : ExpenseRepository {
        return FirebaseExpenseRepository()
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
    factory { CreateExpenseUseCase(repo = provideExpenseRepository()) }
    factory { GetExpensesUseCase(repo = provideExpenseRepository()) }
    viewModel { ExpenseViewModel(
        getExpensesUseCase = get(),
        createExpenseUseCase = get()
    ) }
}
