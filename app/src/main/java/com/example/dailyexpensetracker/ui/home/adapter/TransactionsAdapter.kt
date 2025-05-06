package com.example.dailyexpensetracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyexpensetracker.databinding.CellItemsTransactionBinding
import com.example.dailyexpensetracker.main.models.TransactionModel

class TransactionsAdapter(private val transactions: List<TransactionModel>):RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            CellItemsTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class TransactionViewHolder(val binding: CellItemsTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: TransactionModel) {
//            binding.image.setImageResource(transaction.image)
            binding.title.text = transaction.title
            binding.cost.text = transaction.cost
            binding.date.text = transaction.date

        }

    }
}