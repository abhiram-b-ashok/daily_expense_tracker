package com.example.dailyexpensetracker.ui.add_expenses_or_income.category_bottomsheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyexpensetracker.databinding.CellItemsCategoryBinding

class CategoryAdapter(private val list: List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
     var onItemClick: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CellItemsCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class CategoryViewHolder(private val binding: CellItemsCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, onItemClick: ((Category) -> Unit)?) {
            binding.categoryName.text = category.name
            binding.root.setOnClickListener {
                onItemClick?.invoke(category)
            }
        }


    }
}