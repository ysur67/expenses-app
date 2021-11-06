package com.example.data.models

import com.google.firebase.Timestamp


data class Expense(
    val id: String,
    val title: String,
    val date: Timestamp,
    val money: Float
)
