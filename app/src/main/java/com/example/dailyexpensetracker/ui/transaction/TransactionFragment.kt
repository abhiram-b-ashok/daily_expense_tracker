package com.example.dailyexpensetracker.ui.transaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentTransactionBinding
import com.example.dailyexpensetracker.room_database.MyDatabase
import com.example.dailyexpensetracker.ui.transaction.adapter.expenses.ExpensesAdapter
import com.example.dailyexpensetracker.ui.transaction.adapter.income.IncomeAdapter
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


class TransactionFragment : Fragment() {
 private lateinit var binding: FragmentTransactionBinding
    private lateinit var incomeAdapter: IncomeAdapter
    private lateinit var expenseAdapter: ExpensesAdapter
    private var selectedType: Int? = null
    private var date: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = MyDatabase.getDatabase(requireContext())
        val incomes = database.databaseDao().getIncomes()
        val expenses = database.databaseDao().getExpenses()

        fun updateList() {
            if (selectedType == 1) {
                incomeAdapter = IncomeAdapter(incomes.filter { it.date == date })
                binding.transactionRecyclerView.adapter = incomeAdapter
            }
            if (selectedType == 2) {
                expenseAdapter = ExpensesAdapter(expenses.filter { it.date == date })
                binding.transactionRecyclerView.adapter = expenseAdapter
            }

        }



        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
            updateList()
        }

        selectedType = selectType(1)
        incomeAdapter = IncomeAdapter(incomes)
        binding.transactionRecyclerView.adapter = incomeAdapter


        binding.apply {

            pickDate.setOnClickListener{
                DatePickerDialog(
                    requireContext(),
                    datePicker,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()

            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            incomeButton.setOnClickListener {
                selectedType = selectType(1)
                if(date == null)
                {
                    incomeAdapter = IncomeAdapter(incomes)
                    transactionRecyclerView.adapter = incomeAdapter
                }
                else{
                    incomeAdapter = IncomeAdapter(incomes.filter { it.date == date })
                    transactionRecyclerView.adapter = incomeAdapter
                }
            }

            expenseButton.setOnClickListener {
                selectedType = selectType(2)
                if(date == null)
                {
                    expenseAdapter = ExpensesAdapter(expenses)
                    transactionRecyclerView.adapter = expenseAdapter
                }
                else {
                    expenseAdapter = ExpensesAdapter(expenses.filter { it.date == date })
                    transactionRecyclerView.adapter = expenseAdapter
                }
            }
        }

    }
    private fun selectType(type:Int):Int {
        binding.apply {
            if (type == 1) {
                incomeButtonLayout.setBackgroundResource(R.drawable.gradient_background)
                expenseButtonLayout.setBackgroundResource(R.color.white)
                incomeLabel.setTextColor(resources.getColor(R.color.white))
                expenseLabel.setTextColor(resources.getColor(R.color.grey))
            }
            if (type == 2) {
                expenseButtonLayout.setBackgroundResource(R.drawable.gradient_background)
                incomeButtonLayout.setBackgroundResource(R.color.white)
                expenseLabel.setTextColor(resources.getColor(R.color.white))
                incomeLabel.setTextColor(resources.getColor(R.color.grey))
            }

        }
        return type
    }
    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "yyyy/MM/dd"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.listDate.text = sdf.format(myCalendar.time)
        date = sdf.format(myCalendar.time)

    }



}