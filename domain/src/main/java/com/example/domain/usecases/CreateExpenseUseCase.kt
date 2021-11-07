package com.example.domain.usecases

import com.example.domain.ExpenseRepository
import com.example.domain.models.Expense
import com.example.domain.models.Result
import com.example.domain.params.CreateExpenseParam

class CreateExpenseUseCase(
    private val repo: ExpenseRepository
) {
    suspend fun execute(params: CreateExpenseParam) : Result<Boolean> {
        return repo.createExpense(Expense(
            id = params.title,
            title = params.title,
            date = params.date,
            money = params.money
        ))
    }
}
