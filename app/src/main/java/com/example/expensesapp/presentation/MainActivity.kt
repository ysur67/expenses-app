package com.example.expensesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.expensesapp.databinding.ActivityMainBinding
import com.example.expensesapp.domain.ExpenseViewModel
import com.example.expensesapp.utils.RequestState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val expenseViewModel: ExpenseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val result = expenseViewModel.fetchExpenses()
            result.observe(this, {
                when(it) {
                    is RequestState.Success -> {
                        it.data.forEach {
                            Log.e("tag", "EXPENSE ${it.title}")
                        }
                    }
                    is RequestState.Error -> {
                        Log.e("tag", it.exception.toString())
                    }
                }
            })
        }
    }
}
