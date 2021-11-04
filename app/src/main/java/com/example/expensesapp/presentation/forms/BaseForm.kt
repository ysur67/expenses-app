package com.example.expensesapp.presentation.forms

import android.widget.EditText

abstract class BaseForm {
    protected val fields = ArrayList<EditText>()
    protected val errors: HashMap<EditText, String> = HashMap()
    val isValid = errors.size == 0

    open fun validate() {
        fields.forEach {
            if (it.text.isNullOrBlank()) {
                errors[it] = "This field can't be empty"
            }
        }
    }

    fun displayErrors() {
        fields.forEach {
            it.error = errors[it]
        }
    }
}
