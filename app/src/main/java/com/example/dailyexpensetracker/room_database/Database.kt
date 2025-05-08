package com.example.dailyexpensetracker.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseModel::class], version =1 )
abstract class MyDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): MyDatabase {
            return (INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE = instance
                instance
            }) as MyDatabase
        }




    }
}