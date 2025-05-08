package com.example.dailyexpensetracker.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.CellItemsTransactionBinding
import com.example.dailyexpensetracker.models.transaction.TransactionModel
import com.example.dailyexpensetracker.room_database.DatabaseModel


class TransactionsAdapter(private val transactions: List<DatabaseModel>):
    RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            CellItemsTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    inner class TransactionViewHolder(private val binding: CellItemsTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: DatabaseModel) {

            Log.d("@@testData", ">>> $transaction ")
            binding.title.text = transaction.category
            binding.cost.text = transaction.price.toString()
            binding.date.text = transaction.date
            if (transaction.type == 1) {
                binding.image.setImageResource(R.drawable.ic_arrow_down)
            } else {
                binding.image.setImageResource(R.drawable.ic_arrow_up)
            }

        }

    }
}