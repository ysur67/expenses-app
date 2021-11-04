package com.example.expensesapp.presentation.forms

import android.widget.EditText

class LoginForm(loginField: EditText, passwordField: EditText) : BaseForm() {
    init {
        this.fields.add(loginField)
        this.fields.add(passwordField)
    }
    val login = loginField.text.toString()
    val password = passwordField.text.toString()
}
