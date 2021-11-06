package com.example.expensesapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Result
import com.example.domain.models.User
import com.example.domain.params.LoginUserParam
import com.example.domain.params.RegisterUserParam
import com.example.domain.usecases.CheckIfUserIsAuthenticatedUseCase
import com.example.domain.usecases.LoginUserUseCase
import com.example.domain.usecases.RegisterUserUseCase
import com.example.expensesapp.utils.RequestState
import kotlinx.coroutines.launch

class UserViewModel constructor(
    private val registerUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUserUseCase,
    private val isAuthenticatedUseCase: CheckIfUserIsAuthenticatedUseCase
) : ViewModel() {
    private val _isAuthenticated = MutableLiveData(isAuthenticatedUseCase.execute())
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    fun register(email: String, password: String) : LiveData<RequestState<User>> {
        val requestState = MutableLiveData<RequestState<User>>()
        requestState.postValue(RequestState.Loading())
        viewModelScope.launch {
            when(val result = registerUseCase.execute(RegisterUserParam(email, password))) {
                is Result.Success -> {
                    requestState.postValue(RequestState.Success(result.data))
                    _isAuthenticated.postValue(isAuthenticatedUseCase.execute())
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
                    requestState.postValue(RequestState.Success(result.data))
                }
                is Result.Error -> {
                    requestState.postValue(RequestState.Error(result.exception))
                }
            }
            _isAuthenticated.postValue(isAuthenticatedUseCase.execute())
        }
        return requestState
    }
}
