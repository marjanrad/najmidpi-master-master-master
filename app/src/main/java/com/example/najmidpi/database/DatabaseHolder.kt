package com.example.najmidpi.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.najmidpi.database.tables.SensorTable

@Database(entities = [SensorTable::class], version = 2)
abstract class DatabaseHolder : RoomDatabase() {

    abstract fun databaseController(): DatabaseController

    companion object {

        private const val DATABASE_NAME = "Health2"
        private var INSTANCE: DatabaseHolder? = null

        fun getInstance(context: Context): DatabaseHolder {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context, DatabaseHolder::class.java, DATABASE_NAME
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}
