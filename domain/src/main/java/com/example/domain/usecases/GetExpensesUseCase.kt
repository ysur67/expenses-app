package com.example.domain.usecases

import com.example.domain.ExpenseRepository
import com.example.domain.models.Expense
import com.example.domain.models.Result

class GetExpensesUseCase(private val repo: ExpenseRepository) {
    suspend fun execute() : Result<List<Expense>> {
        return repo.getExpenses()
    }
}
