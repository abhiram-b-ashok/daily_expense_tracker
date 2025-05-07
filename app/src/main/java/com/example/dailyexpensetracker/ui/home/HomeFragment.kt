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
import com.example.dailyexpensetracker.ui.home.adapter.TransactionsAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var transactionAdapter: TransactionsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewAllButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_transactionFragment) }

        val list = arrayListOf(
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "Food", "200", "23-09-22"),
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "wedd", "323", "23-09-22"),
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "Food", "200", "23-09-22"),
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "dvddv", "32", "23-09-22"),
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "Food", "210", "23-09-22"),
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "fcdc", "123", "23-09-22"),
            TransactionModel("https://www.pngitem.com/pimgs/m/209-2095726_alarm-clock-square-shaped-objects-clipart-hd-png.png", "Food", "200", "23-09-22"),

        )


        transactionAdapter = TransactionsAdapter(list)
        binding.transactionRecyclerView.adapter = transactionAdapter
        transactionAdapter.notifyDataSetChanged()
        }



    }


