package com.example.dailyexpensetracker.room_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: DatabaseModel)

    @Query("SELECT * FROM database_table WHERE type = 1" )
    suspend fun getIncomes(): List<DatabaseModel>


    @Query("SELECT * FROM database_table WHERE type = 2" )
    suspend fun getExpenses(): List<DatabaseModel>



}