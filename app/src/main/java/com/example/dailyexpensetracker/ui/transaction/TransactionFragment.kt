package com.example.dailyexpensetracker.ui.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dailyexpensetracker.R
import com.example.dailyexpensetracker.databinding.FragmentTransactionBinding


class TransactionFragment : Fragment() {
 private lateinit var binding: FragmentTransactionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            incomeButton.setOnClickListener {
                selectType(1)

            }
            expenseButton.setOnClickListener {
                selectType(2)

            }
        }




    }
    private fun selectType(type:Int) {
        binding.apply {
            if (type == 1) {
                incomeButton.setCardBackgroundColor(R.drawable.gradient_background)
                expenseButton.setCardBackgroundColor(resources.getColor(R.color.white))
                incomeLabel.setTextColor(resources.getColor(R.color.white))
            }
            if (type == 2) {
                expenseButton.setCardBackgroundColor(R.drawable.gradient_background)
                incomeButton.setCardBackgroundColor(resources.getColor(R.color.white))
                expenseLabel.setTextColor(resources.getColor(R.color.white))
            }

        }
    }


}