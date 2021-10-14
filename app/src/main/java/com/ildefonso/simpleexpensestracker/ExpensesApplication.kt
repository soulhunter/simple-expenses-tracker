package com.ildefonso.simpleexpensestracker

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ExpensesApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ExpensesRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ExpenseRepository(database.expenseDao()) }
}