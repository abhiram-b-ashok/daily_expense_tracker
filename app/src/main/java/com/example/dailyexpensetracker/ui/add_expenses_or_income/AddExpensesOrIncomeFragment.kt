package com.example.dailyexpensetracker.ui.add_expenses_or_income

import android.app.DatePickerDialog
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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentAddExpensesOrIncomeBinding
import com.example.dailyexpensetracker.room_database.DatabaseModel
import com.example.dailyexpensetracker.room_database.MyDatabase
import com.example.dailyexpensetracker.ui.add_expenses_or_income.category_bottomsheet.CategoryBottomSheetFragment
import com.example.dailyexpensetracker.ui.add_expenses_or_income.date_bottomsheet.DateBottomSheetFragment
import com.example.dailyexpensetracker.ui.add_expenses_or_income.type_bottom_sheet.TypeBottomSheetFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddExpensesOrIncomeFragment : Fragment() {
    private lateinit var binding: FragmentAddExpensesOrIncomeBinding
    private val typeBottomSheetFragment = TypeBottomSheetFragment()
    private val categoryBottomSheetFragment = CategoryBottomSheetFragment()
//    private val dateBottomSheetFragment = DateBottomSheetFragment()
    private var selected:Int? = null
    private lateinit var database: MyDatabase



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddExpensesOrIncomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = MyDatabase.getDatabase(requireContext())

        val textView = binding.etPrice
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(0f, 0f, width, textView.textSize, intArrayOf(
            Color.parseColor("#F97C3C"),
            Color.parseColor("#FDB54E"),
            Color.parseColor("#8446CC")
        ), null, Shader.TileMode.REPEAT)

        textView.paint.setShader(textShader)
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }


        binding.apply {
            addTypeLabel.setOnClickListener {
                typeBottomSheetFragment.show(parentFragmentManager, "typeBottomSheetFragment")
                setSelectedTypeResultListener()
            }


            category.setOnClickListener {
                categoryBottomSheetFragment.show(parentFragmentManager, "categoryBottomSheetFragment")
                setSelectedCategoryResultListener()

            }
            noteLayout.setOnClickListener {
                etNote.requestFocus()
                etNote.showKeyboard()
            }
            priceLayout.setOnClickListener {
                etPrice.requestFocus()
                etPrice.showKeyboard()
            }

            date.setOnClickListener {
//                dateBottomSheetFragment.show(parentFragmentManager, "dateBottomSheetFragment")
//                setSelectedDateResultListener()
                DatePickerDialog(
                    requireContext(),
                    datePicker,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()

            }

            closeButton.setOnClickListener {
                findNavController().navigate(R.id.action_addExpensesOrIncomeFragment_to_homeFragment)
            }
            saveButton.setOnClickListener {
                if (validateInputs()) {

                    val type = selected
                    val price = etPrice.text.trim().toString()
                    val note = etNote.text.toString()
                    val date = dateLabel.text.toString()
                    val category = categoryLabel.text.toString()

                    val addDataList = DatabaseModel(
                        type = type,
                        price = price.toInt(),
                        note = note,
                        date = date,
                        category = category
                    )
                    database.databaseDao().insert(addDataList)
                    Toast.makeText(this@AddExpensesOrIncomeFragment.context, "Saved", Toast.LENGTH_SHORT).show()

                    binding.apply {
                       etPrice.setText(getString(R.string.empty))
                        etNote.setText(getString(R.string.empty))
                        dateLabel.text = " "
                        categoryLabel.text = " "

                        etPrice.focusable
                        etNote.focusable
                        dateLabel.focusable
                        categoryLabel.focusable

                    }
                }
                else {
                    Toast.makeText(this@AddExpensesOrIncomeFragment.context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    private fun setSelectedDateResultListener() {
//        parentFragmentManager.setFragmentResultListener(
//            DateBottomSheetFragment.SELECTED_DATE_ID,
//            viewLifecycleOwner
//        ){
//            _, bundle ->
//            val selectedDate = bundle.getString(DateBottomSheetFragment.SELECTED_DATE)
//            Log.d("selectedDate", selectedDate.toString())
//            binding.dateLabel.text = selectedDate
//        }
//    }

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


    private fun setSelectedTypeResultListener() {
        parentFragmentManager.setFragmentResultListener(
            TypeBottomSheetFragment.SELECTED_SCHEME_ID,
            viewLifecycleOwner
        ) { _, bundle ->
            val selectedType = bundle.getInt(TypeBottomSheetFragment.SELECTED_TYPE)
            selected = selectedType
            Log.d("selectedType", "$selected")
            if (selected == 1) {
                binding.addTypeLabel.setText(getString(R.string.add_income))
            } else if (selected == 2) {
                binding.addTypeLabel.setText(getString(R.string.add_expenses))
            }

        }
    }
    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "yyyy/MM/dd"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.dateLabel.text = sdf.format(myCalendar.time)
    }

    private fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
    private fun validateInputs(): Boolean {
        var isValid = true
        binding.apply {
            if(addTypeLabel.text.isNullOrEmpty())
            {
                isValid = false
            }
            if (etPrice.text.isNullOrEmpty()) {

                isValid = false
            }
            if (etNote.text.isNullOrEmpty()) {

                    isValid = false
            }
            if (dateLabel.text.isNullOrEmpty()) {

                isValid = false
            }
            if (categoryLabel.text.isNullOrEmpty()) {

                isValid = false
            }
        }
                return isValid
    }
}
