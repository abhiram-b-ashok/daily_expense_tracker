package com.example.dailyexpensetracker.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "database_table")
data class DatabaseModel(

    @PrimaryKey val id: Int? = null,
    val type: Int,
    val title: String,
    val image: String,
    val date: String,
    val amount: String,
    val category: String,
    val note: String,
    var isSaved: Boolean = false
)



