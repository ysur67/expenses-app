package com.example.expensesapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val result = viewModel.register("yse", "asdffsd")
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