package com.example.domain

import com.example.domain.models.Expense
import java.util.*
import com.example.domain.models.Result

interface ExpenseRepository {
    suspend fun createExpense(model: Expense):  Result<Boolean>
    suspend fun getExpenses(): Result<List<Expense>>
}
