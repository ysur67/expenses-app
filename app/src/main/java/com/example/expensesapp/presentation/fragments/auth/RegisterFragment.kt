package com.example.expensesapp.presentation.fragments.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.allViews
import androidx.core.view.forEach
import com.example.expensesapp.databinding.FragmentRegisterBinding
import com.example.expensesapp.domain.UserViewModel
import com.example.expensesapp.presentation.forms.RegisterForm
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : BaseAuthFragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding!!

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            val form = RegisterForm(
                binding.editEmail,
                binding.editPassword,
                binding.editPasswordRepeat
            )
            form.validate()
            if (!form.isValid) {
                form.displayErrors()
                return@setOnClickListener
            }
            val result = userViewModel.register(form.email, form.password)
            result.observe(viewLifecycleOwner, {
                handleRequestState(it)
            })
        }
    }

    override fun toggleLoadingFragment(isActive: Boolean) {
        requireActivity().runOnUiThread {
            binding.loadingFragment.root.visibility = if (isActive) View.VISIBLE else View.GONE
            binding.loadingFragment.root.forEach {
                it.visibility = if (isActive) View.VISIBLE else View.GONE
            }
            binding.root.allViews.forEach {
                it.isEnabled = isActive.not()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment RegisterFragment.
         */
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}