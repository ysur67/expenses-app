package com.example.expensesapp

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.MockUserRepo
import com.example.domain.models.Result
import com.example.domain.params.RegisterUserParam
import com.example.domain.usecases.LoginUserUseCase
import com.example.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.launch

class UserViewModel(
//    private val registerUseCase: RegisterUserUseCase,
//    private val loginUseCase: LoginUserUseCase
) : ViewModel() {
    private val userRepo = MockUserRepo()
    private val registerUseCase = RegisterUserUseCase(userRepo)

    private val _currentUser: MutableLiveData<User> = MutableLiveData(null)
    val user: LiveData<User>
        get() = _currentUser

    fun register(email: String, password: String) : LiveData<RequestState<User>> {
        val requestState = MutableLiveData<RequestState<User>>()
        viewModelScope.launch {
            requestState.postValue(RequestState.Loading())
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
}

fun com.example.domain.models.User.toModel() : User {
    return User(this.email)
}