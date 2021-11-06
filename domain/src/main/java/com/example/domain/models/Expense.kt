package com.example.domain.models

import java.util.*

data class Expense(
    val id: String,
    val title: String,
    val date: Date,
    val money: Float
)
