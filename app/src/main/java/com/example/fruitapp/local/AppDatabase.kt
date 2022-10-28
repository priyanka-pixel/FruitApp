package com.example.fruitapp.local

import android.content.Context
import androidx.room.*
import com.example.fruitapp.models.FruitsItem

@Database(entities = [FruitsItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fruitsDAO(): FruitsDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "FruitApp")
                .fallbackToDestructiveMigration()
                .build()

    }
}