package com.example.expensesapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Expense
import com.example.domain.models.Result
import com.example.domain.usecases.CreateExpenseUseCase
import com.example.domain.usecases.GetExpensesUseCase
import com.example.expensesapp.utils.RequestState
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val createExpenseUseCase: CreateExpenseUseCase
) : ViewModel() {
    private val _expenses: MutableLiveData<ArrayList<Expense>> = MutableLiveData(ArrayList())
    val expenses: LiveData<ArrayList<Expense>> = _expenses

    fun fetchExpenses() : LiveData<RequestState<List<Expense>>> {
        val requestState = MutableLiveData<RequestState<List<Expense>>>()
        requestState.postValue(RequestState.Loading())
        viewModelScope.launch {
            when(val result = getExpensesUseCase.execute()) {
                is Result.Success -> {
                    requestState.postValue(RequestState.Success(result.data))
                    _expenses.postValue(result.data as ArrayList<Expense>)
                }
                is Result.Error -> {
                    requestState.postValue(RequestState.Error(result.exception))
                }
            }
        }
        return requestState
    }
}