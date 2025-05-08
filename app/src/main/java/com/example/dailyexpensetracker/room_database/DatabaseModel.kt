package com.example.dailyexpensetracker.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "database_table")
data class DatabaseModel(

    @PrimaryKey val id: Int? = null,
    val type: Int?= null,
    val date: String?= null,
    val price: Int,
    val category: String?= null,
    val note: String?= null,
    var isSaved: Boolean = false
)



