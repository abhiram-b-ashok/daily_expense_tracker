package com.example.dailyexpensetracker.ui.add_expenses_or_income

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentAddExpensesOrIncomeBinding
import com.example.dailyexpensetracker.ui.add_expenses_or_income.category_bottomsheet.CategoryBottomSheetFragment
import com.example.dailyexpensetracker.ui.add_expenses_or_income.date_bottomsheet.DateBottomSheetFragment
import com.example.dailyexpensetracker.ui.add_expenses_or_income.type_bottom_sheet.TypeBottomSheetFragment


class AddExpensesOrIncomeFragment : Fragment() {
    private lateinit var binding: FragmentAddExpensesOrIncomeBinding
    private val typeBottomSheetFragment = TypeBottomSheetFragment()
    private val categoryBottomSheetFragment = CategoryBottomSheetFragment()
    private val dateBottomSheetFragment = DateBottomSheetFragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddExpensesOrIncomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = binding.price
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(0f, 0f, width, textView.textSize, intArrayOf(
            Color.parseColor("#F97C3C"),
            Color.parseColor("#FDB54E"),
            /*Color.parseColor("#64B678"),
            Color.parseColor("#478AEA"),*/
            Color.parseColor("#8446CC")
        ), null, Shader.TileMode.REPEAT)

        textView.paint.setShader(textShader)





        binding.apply {
            addTypeLabel.setOnClickListener {
                typeBottomSheetFragment.show(parentFragmentManager, "typeBottomSheetFragment")
            }
            setSelectedSchemeResultListener()

            category.setOnClickListener {
                categoryBottomSheetFragment.show(parentFragmentManager, "categoryBottomSheetFragment")
                setSelectedCategoryResultListener()

            }
            noteLayout.setOnClickListener {
                note.requestFocus()
                note.showKeyboard()
            }

            date.setOnClickListener {
                dateBottomSheetFragment.show(parentFragmentManager, "dateBottomSheetFragment")
                setSelectedDateResultListener()

            }

            closeButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

        }


    }

    private fun setSelectedDateResultListener() {
        parentFragmentManager.setFragmentResultListener(
            DateBottomSheetFragment.SELECTED_DATE_ID,
            viewLifecycleOwner
        ){
            _, bundle ->
            val selectedDate = bundle.getString(DateBottomSheetFragment.SELECTED_DATE)
            Log.d("selectedDate", selectedDate.toString())
            binding.dateLabel.text = selectedDate
        }
    }

    private fun setSelectedCategoryResultListener() {
        parentFragmentManager.setFragmentResultListener(
            CategoryBottomSheetFragment.SELECTED_CATEGORY_ID,
            viewLifecycleOwner
        ) { _, bundle ->
            val selectedCategory = bundle.getString(CategoryBottomSheetFragment.SELECTED_CATEGORY)
            Log.d("selectedCategory", selectedCategory.toString())
            binding.categoryLabel.text = selectedCategory
        }
    }


    private fun setSelectedSchemeResultListener() {
        parentFragmentManager.setFragmentResultListener(
            TypeBottomSheetFragment.SELECTED_SCHEME_ID,
            viewLifecycleOwner
        ) { _, bundle ->
            val selectedType = bundle.getString(TypeBottomSheetFragment.SELECTED_TYPE)
            Log.d("selectedType", selectedType.toString())
            binding.addTypeLabel.setText(selectedType)
        }
    }

    private fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
