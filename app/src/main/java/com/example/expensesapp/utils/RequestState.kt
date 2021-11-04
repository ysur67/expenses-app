package com.example.expensesapp.utils

import java.lang.Exception

sealed class RequestState<out T> {
    class Loading<T> : RequestState<T>()
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val exception: Exception) : RequestState<Nothing>()
}
