package com.example.dailyexpensetracker.ui.transaction.adapter.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailyexpensetracker.databinding.CellItemsExpensesBinding
import com.example.dailyexpensetracker.room_database.DatabaseModel

class ExpensesAdapter(private val selectedTypes: List<DatabaseModel>) : RecyclerView.Adapter<ExpensesAdapter.SelectedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        val binding= CellItemsExpensesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SelectedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
       holder.bind(selectedTypes[position])
    }

    override fun getItemCount(): Int {
       return selectedTypes.size
    }


    class SelectedViewHolder(private val binding: CellItemsExpensesBinding ) :RecyclerView.ViewHolder(binding.root) {
        fun bind(selectedType: DatabaseModel) {
            binding.apply {

                note.text=selectedType.note
                title.text=selectedType.category
                cost.text=selectedType.price.toString()

            }
        }

    }


}