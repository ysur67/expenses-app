package com.example.expensesapp.presentation.fragments.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.expensesapp.R

class ErrorOccurredFragment(private val message: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = requireActivity()
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.an_error_occurred)
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.cancel()
            }
        return builder.create()
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
