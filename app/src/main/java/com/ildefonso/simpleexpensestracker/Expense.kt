package com.ildefonso.simpleexpensestracker

import androidx.room.*
import java.util.*

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val  date: Date,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "description") val description: String
)
