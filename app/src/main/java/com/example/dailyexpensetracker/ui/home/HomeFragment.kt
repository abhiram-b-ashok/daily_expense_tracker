package com.example.dailyexpensetracker.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentHomeBinding
import com.example.dailyexpensetracker.models.transaction.TransactionModel
import com.example.dailyexpensetracker.room_database.MyDatabase
import com.example.dailyexpensetracker.ui.home.adapter.TransactionsAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var transactionAdapter: TransactionsAdapter
    private var totalIncome :Int =0
    private var totalExpense : Int =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = MyDatabase.getDatabase(requireContext())
        val list = database.databaseDao().getAll()
        val incomes = database.databaseDao().getIncomes()
        val expenses = database.databaseDao().getExpenses()


        incomes.forEach{
            totalIncome += it.price
        }
        expenses.forEach{
            totalExpense += it.price
        }
        binding.balance.text = (totalIncome - totalExpense).toString()
        binding.income.text = totalIncome.toString()
        binding.expenses.text = totalExpense.toString()


        binding.viewAllButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_transactionFragment) }




        transactionAdapter = TransactionsAdapter(list)
        binding.transactionRecyclerView.adapter = transactionAdapter
        transactionAdapter.notifyDataSetChanged()
        }



    }


