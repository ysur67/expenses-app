package com.example.expensesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensesapp.databinding.ActivityMainBinding
import com.example.expensesapp.domain.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val userViewModel: UserViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
