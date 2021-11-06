package com.example.expensesapp.presentation.fragments

import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.allViews
import androidx.core.view.forEach
import com.example.domain.models.Result
import com.example.domain.models.User
import com.example.expensesapp.databinding.FragmentLoginBinding
import com.example.expensesapp.domain.UserViewModel
import com.example.expensesapp.presentation.forms.LoginForm
import com.example.expensesapp.utils.RequestState
import com.example.expensesapp.utils.displaySnack
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            val form = LoginForm(binding.editLoginText, binding.editPassword)
            form.validate()
            if (!form.isValid) {
                form.displayErrors()
                return@setOnClickListener
            }
            val result = userViewModel.login(form.login, form.password)
            result.observe(viewLifecycleOwner, {
                when(it) {
                    is RequestState.Success -> onSuccess(it.data)
                    is RequestState.Loading -> onLoading()
                    is RequestState.Error -> onError(it.exception)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSuccess(user: User) {
        displaySnack("Success, ${user.email}")
    }

    private fun onLoading() {
        toggleLoadingFragment(true)
    }

    private fun onError(err: Exception) {
        toggleLoadingFragment(false)
        displaySnack(err.toString())
    }

    private fun toggleLoadingFragment(isActive: Boolean) {
        binding.loadingFragment.root.forEach {
            it.visibility = if (isActive) View.VISIBLE else View.GONE
        }
        binding.root.allViews.forEach {
            it.isEnabled = isActive.not()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         *
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) = LoginFragment()
    }
}