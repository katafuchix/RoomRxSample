package com.example.roomrxsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = SampleDatabase.DATABASE_VERSION)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "sample.db"

        private var instance: SampleDatabase? = null

        fun init(context: Context) {
            Room.databaseBuilder(context, SampleDatabase::class.java, DATABASE_NAME)
                .build().also { instance = it }
        }

        fun getInstance() = instance
    }
}