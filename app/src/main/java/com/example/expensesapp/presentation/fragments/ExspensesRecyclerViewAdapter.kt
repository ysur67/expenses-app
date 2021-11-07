package com.example.expensesapp.presentation.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.domain.models.Expense
import com.example.expensesapp.databinding.FragmentItemBinding


/**
 * [RecyclerView.Adapter] that can display a [Expense].
 */
class ExspensesRecyclerViewAdapter(
    private val values: List<Expense>
) : RecyclerView.Adapter<ExspensesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = (position + 1).toString()
        holder.titleView.text = item.title
        holder.dateView.text = item.date.toString()
        holder.moneyView.text = item.money.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.expenseNumber
        val titleView: TextView = binding.expenseTitle
        val dateView: TextView = binding.expenseDate
        val moneyView: TextView = binding.expenseMoney
    }
}
