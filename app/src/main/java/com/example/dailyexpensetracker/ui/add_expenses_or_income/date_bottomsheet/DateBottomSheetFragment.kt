package com.example.dailyexpensetracker.ui.add_expenses_or_income.date_bottomsheet

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentDateBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class DateBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDateBottomSheetBinding

    companion object {
        const val SELECTED_DATE_ID = "SELECTED_DATE_ID"
        const val SELECTED_DATE = "SELECTED_DATE"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDateBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val datePicker = datePicker
            datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"

                val bundle = Bundle()
                bundle.putString(SELECTED_DATE, selectedDate)
                parentFragmentManager.setFragmentResult(SELECTED_DATE_ID, bundle)
                dismiss()


            }


        }


    }
}