package com.example.expensesapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.models.Result
import com.example.expensesapp.databinding.FragmentLoginBinding
import com.example.expensesapp.domain.UserViewModel
import com.example.expensesapp.presentation.forms.LoginForm
import com.example.expensesapp.utils.RequestState
import com.example.expensesapp.utils.displaySnack
import org.koin.androidx.viewmodel.ext.android.viewModel


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
                    is RequestState.Loading -> {
                        displaySnack("loading")
                    }
                    is RequestState.Error -> {
                        displaySnack("error: ${it.exception}")
                    }
                    is RequestState.Success -> {
                        displaySnack("Success")
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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