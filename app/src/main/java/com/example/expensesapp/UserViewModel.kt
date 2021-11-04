package com.example.expensesapp

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.FirebaseUserRepository
import com.example.domain.models.Result
import com.example.domain.params.LoginUserParam
import com.example.domain.params.RegisterUserParam
import com.example.domain.usecases.LoginUserUseCase
import com.example.domain.usecases.RegisterUserUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepo = FirebaseUserRepository(firebase = FirebaseAuth.getInstance())
    private val registerUseCase = RegisterUserUseCase(userRepo)
    private val loginUseCase = LoginUserUseCase(userRepo)

    private val _currentUser: MutableLiveData<User> = MutableLiveData(null)
    val user: LiveData<User>
        get() = _currentUser

    fun register(email: String, password: String) : LiveData<RequestState<User>> {
        val requestState = MutableLiveData<RequestState<User>>()
        requestState.postValue(RequestState.Loading())
        viewModelScope.launch {
            when(val result = registerUseCase.execute(RegisterUserParam(email, password))) {
                is Result.Success -> {
                    requestState.postValue(RequestState.Success(result.data.toModel()))
                }
                is Result.Error -> {
                    requestState.postValue(RequestState.Error(result.exception))
                }
            }
        }
        return requestState
    }
    fun login(email: String, password: String) : LiveData<RequestState<User>> {
        val requestState = MutableLiveData<RequestState<User>>()
        requestState.postValue(RequestState.Loading())
        viewModelScope.launch {
            when(val result = loginUseCase.execute(LoginUserParam(email, password))) {
                is Result.Success -> {
                    requestState.postValue(RequestState.Success(result.data.toModel()))
                }
                is Result.Error -> {
                    requestState.postValue(RequestState.Error(result.exception))
                }
            }
        }
        return requestState
    }
}

fun com.example.domain.models.User.toModel() : User {
    return User(this.email)
}