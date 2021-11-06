package com.example.data.repositories

import com.example.domain.ExpenseRepository
import com.example.domain.models.Expense
import com.example.domain.models.Result
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*

class FirebaseExpenseRepository : ExpenseRepository {
    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun createExpense(model: Expense): Result<Boolean> {
        return try {
            firestore.collection(EXPENSES_TAG).add(model).await()
            Result.Success(true)
        } catch (err: Exception) {
            Result.Error(err)
        }
    }

    override suspend fun getExpenses(): Result<List<Expense>> {
        return try {
            val result = ArrayList<Expense>()
            firestore.collection(EXPENSES_TAG).get().await().forEach {
                result.add(it.toModel())
            }
            return Result.Success(result)
        } catch (err: Exception) {
            Result.Error(err)
        }
    }

    companion object {
        const val EXPENSES_TAG = "expenses"
    }
}

fun QueryDocumentSnapshot.toModel() : Expense {
    return Expense(
        id = getString("id")!!,
        title = getString("title")!!,
        date = getTimestamp("date")!!.toDate(),
        money = getDouble("money")!!.toFloat()
    )
}
