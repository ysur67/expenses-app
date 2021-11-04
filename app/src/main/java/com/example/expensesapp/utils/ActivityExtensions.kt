package com.example.expensesapp.utils

import android.app.Activity
import android.widget.Toast


fun Activity.displayToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
