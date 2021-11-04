package com.example.expensesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.expensesapp.R
import com.example.expensesapp.domain.UserViewModel
import com.example.expensesapp.utils.RequestState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val result = userViewModel.register("dfds@fdsfd.com", "asdffsd")
            result.observe(this, {
                when (it) {
                    is RequestState.Loading -> {
                        Log.e("DEBUG", "LOADING LOADING LOADING")
                    }
                    is RequestState.Success -> {
                        Log.e("DEBUG", "SUCCESS SUCCESS SUCCESS")
                    }
                    is RequestState.Error -> {
                        Log.e("DEBUG", "${it.exception}")
                    }
                }
            })
        }
    }
}