package com.example.expensesapp.utils

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun Fragment.displaySnack(text: String) {
    Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show()
}
