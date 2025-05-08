package com.example.dailyexpensetracker.room_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(expense: DatabaseModel)

    @Query("SELECT * FROM database_table WHERE type = 1" )
     fun getIncomes(): List<DatabaseModel>

    @Query("SELECT * FROM database_table WHERE type = 2" )
     fun getExpenses(): List<DatabaseModel>

     @Query("SELECT * FROM database_table")
     fun getAll(): List<DatabaseModel>



}