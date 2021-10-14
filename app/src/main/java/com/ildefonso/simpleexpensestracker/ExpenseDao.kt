package com.ildefonso.simpleexpensestracker

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getExpensesByDate(): Flow<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Query("DELETE FROM expenses")
    suspend fun deleteAll()
}