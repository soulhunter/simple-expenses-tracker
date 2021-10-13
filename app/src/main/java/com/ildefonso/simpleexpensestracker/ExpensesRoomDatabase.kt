package com.ildefonso.simpleexpensestracker

import android.content.Context
import androidx.room.*

// Annotates class to be a Room Database with a table (entity) of the Expense class
@Database(entities = arrayOf(Expense::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class ExpensesRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): ExpenseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ExpensesRoomDatabase? = null

        fun getDatabase(context: Context): ExpensesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpensesRoomDatabase::class.java,
                    "expensesdb"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
