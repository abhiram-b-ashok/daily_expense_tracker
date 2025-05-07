package com.example.dailyexpensetracker.models.transaction


data class TransactionModel(
    val image: String ?=null,
    val title: String ?=null,
    val cost: String ?=null,
    val date: String ?=null,
)

