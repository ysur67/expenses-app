package com.example.expensesapp.presentation.forms

import android.widget.EditText

class RegisterForm(
    emailField: EditText,
    private val passwordField: EditText,
    passwordRepeatField: EditText
) : BaseForm() {
    init {
        this.fields.add(emailField)
        this.fields.add(passwordField)
        this.fields.add(passwordRepeatField)
    }
    val email = emailField.text.toString()
    val password = passwordField.text.toString()
    val passwordRepeat = passwordRepeatField.text.toString()

    override fun validate() {
        super.validate()
        if (password != passwordRepeat) {
            this.errors[passwordField] = "Passwords don't match"
        }
    }
}