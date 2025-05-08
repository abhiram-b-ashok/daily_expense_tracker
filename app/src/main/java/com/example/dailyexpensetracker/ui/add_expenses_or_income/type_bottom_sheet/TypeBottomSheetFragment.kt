package com.example.dailyexpensetracker.ui.add_expenses_or_income.type_bottom_sheet


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dailyexpensetracker.databinding.FragmentTypeBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TypeBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentTypeBottomSheetBinding

    companion object {
        const val SELECTED_SCHEME_ID = "selected_scheme_id"
        const val SELECTED_TYPE = "type"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTypeBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.income.setOnClickListener {
            sendResult(1)
        }
        binding.expense.setOnClickListener {
            sendResult(2)
        }
    }

    private fun sendResult(type: Int) {
        val bundle = Bundle().apply {
            putInt(SELECTED_TYPE, type)
        }
        parentFragmentManager.setFragmentResult(SELECTED_SCHEME_ID, bundle)
        dismiss()
    }
}