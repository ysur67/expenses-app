package com.example.expensesapp.presentation.fragments

import android.view.View
import androidx.core.view.allViews
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.example.domain.models.User
import com.example.expensesapp.presentation.fragments.utils.ErrorOccurredFragment
import com.example.expensesapp.utils.RequestState
import com.example.expensesapp.utils.displaySnack
import java.lang.Exception

abstract class BaseAuthFragment : Fragment() {
    protected fun handleRequestState(result: RequestState<User>) {
        when(result) {
            is RequestState.Success -> onSuccess(result.data)
            is RequestState.Loading -> onLoading()
            is RequestState.Error -> onError(result.exception)
        }
    }

    protected open fun onSuccess(user: User) {
        toggleLoadingFragment(false)
    }

    protected fun onLoading() {
        toggleLoadingFragment(true)
    }

    protected fun onError(err: Exception) {
        toggleLoadingFragment(false)
        val errorDialog = ErrorOccurredFragment.newInstance(err.message.toString())
        val manager = requireActivity().supportFragmentManager
        val transaction = manager.beginTransaction()
        errorDialog.show(transaction, "dialog")
    }

    protected abstract fun toggleLoadingFragment(isActive: Boolean)
}