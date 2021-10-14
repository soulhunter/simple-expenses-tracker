package com.ildefonso.simpleexpensestracker

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

// Annotates class to be a Room Database with a table (entity) of the Expense class
@Database(entities = arrayOf(Expense::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class ExpensesRoomDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    private class ExpenseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.expenseDao())
                }
            }
        }

        suspend fun populateDatabase(expenseDao: ExpenseDao) {
            expenseDao.deleteAll()
            val format = SimpleDateFormat("yyyy-MM-dd")

            // For a test, add a sample expense here
            var expense = Expense(0, format.parse("2021-10-13"), 15.25, "Just a test expense")
            expenseDao.insert(expense)

        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ExpensesRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExpensesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpensesRoomDatabase::class.java,
                    "expensesdb"
                )
                    .addCallback(ExpenseDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}


