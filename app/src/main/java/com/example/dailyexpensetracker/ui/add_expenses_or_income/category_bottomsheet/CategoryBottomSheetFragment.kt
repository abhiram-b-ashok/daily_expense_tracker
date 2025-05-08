package com.example.dailyexpensetracker.ui.add_expenses_or_income.category_bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentCategoryBottomSheetBinding
import com.example.dailyexpensetracker.ui.add_expenses_or_income.category_bottomsheet.adapter.Category
import com.example.dailyexpensetracker.ui.add_expenses_or_income.category_bottomsheet.adapter.CategoryAdapter
import com.example.dailyexpensetracker.ui.add_expenses_or_income.type_bottom_sheet.TypeBottomSheetFragment.Companion.SELECTED_SCHEME_ID
import com.example.dailyexpensetracker.ui.add_expenses_or_income.type_bottom_sheet.TypeBottomSheetFragment.Companion.SELECTED_TYPE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CategoryBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCategoryBottomSheetBinding
    private lateinit var adapter: CategoryAdapter

    companion object {
        const val SELECTED_CATEGORY_ID = "selected_scheme_id"
        const val SELECTED_CATEGORY = "type"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentCategoryBottomSheetBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val list = arrayListOf(
           Category("Food"),
           Category("Travel"),
           Category("Shopping"),
           Category("Health"),
           Category("Entertainment"),
           Category("Recharge"),
           Category("Salary"),
           Category("Others")
       )
        adapter = CategoryAdapter(list)
        binding.categoryRecycler.adapter = adapter

        adapter.onItemClick = {item ->
            list.forEach {
                it.isSelected = false
            }
            item.isSelected = true
            adapter.notifyDataSetChanged()
            sendResult(item.name)

            dismiss()


        }

    }
    private fun sendResult(type: String) {
        val bundle = Bundle().apply {
            putString(SELECTED_TYPE, type)
        }
        parentFragmentManager.setFragmentResult(SELECTED_SCHEME_ID, bundle)
        dismiss()
    }


}