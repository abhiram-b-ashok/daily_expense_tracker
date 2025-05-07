package com.example.dailyexpensetracker.main.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentMainBinding


class MainFragment : Fragment() {
private lateinit var binding: FragmentMainBinding
private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavMenu.setOnItemSelectedListener {
           when (it.itemId) {
               R.id.homeFragment -> {
                   navController.navigate(R.id.homeFragment)
                   true
               }
               R.id.addExpensesOrIncomeFragment -> {
                   navController.navigate(R.id.addExpensesOrIncomeFragment)
                   false
               }

               else -> {
                   false
               }
           }
        }

    }}


