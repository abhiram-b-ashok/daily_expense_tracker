package com.example.dailyexpensetracker.ui.transaction.adapter.income

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailyexpensetracker.databinding.CellItemsIncomeBinding
import com.example.dailyexpensetracker.room_database.DatabaseModel

class IncomeAdapter(private val incomeList: List<DatabaseModel>) : RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val binding = CellItemsIncomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        holder.bind(incomeList[position])
    }
    override fun getItemCount(): Int {
        return incomeList.size
    }

    class IncomeViewHolder(private val binding: CellItemsIncomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(incomeModel: DatabaseModel) {
            binding.apply {
                title.text = incomeModel.category
                cost.text = incomeModel.price.toString()
                note.text=incomeModel.note
            }
        }

    }

}