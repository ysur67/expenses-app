package com.example.expensesapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensesapp.databinding.FragmentItemListBinding
import com.example.expensesapp.domain.ExpenseViewModel
import com.example.expensesapp.presentation.adapters.ExspensesRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExpenseFragment : Fragment() {
    private var _binding: FragmentItemListBinding? = null
    private val binding = _binding!!

    private val _viewModel: ExpenseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val view = binding.root
        with(view) {
            layoutManager =LinearLayoutManager(context)
            adapter = ExspensesRecyclerViewAdapter(_viewModel.expenses.value ?: ArrayList())
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExpenseFragment()
    }
}