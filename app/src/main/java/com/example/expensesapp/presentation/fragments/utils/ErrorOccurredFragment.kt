package com.example.expensesapp.presentation.fragments.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.expensesapp.R
import com.example.expensesapp.presentation.fragments.LoginFragment
import java.lang.IllegalStateException

class ErrorOccurredFragment(
    private val message: String
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.an_error_occurred)
                .setMessage(message)
                .setPositiveButton("ok") {
                    dialog, id -> dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("asdf")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         *
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance(message: String) = ErrorOccurredFragment(message)
    }
}
