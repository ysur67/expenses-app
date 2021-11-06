package com.example.expensesapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.expensesapp.R
import com.example.expensesapp.databinding.ActivityAuthBinding
import com.example.expensesapp.domain.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {
    val userViewModel: UserViewModel by viewModel()
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)
        userViewModel.isAuthenticated.observe(this, {
            if (it != true) {
                return@observe
            }
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        })
    }
}
