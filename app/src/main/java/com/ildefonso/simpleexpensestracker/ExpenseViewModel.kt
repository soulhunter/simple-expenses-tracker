package com.ildefonso.simpleexpensestracker

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    // TODO: modify this to only get the expenses from the beginning of current month (I care about *this* month expenses by default).  This implies adding some way of moving to previous/next month in the UI, of course.
    val allExpenses: LiveData<List<Expense>> = repository.allExpenses.asLiveData()

    // This is, as far as I know, a kind of async method.  We will likely want a way of reporting the result of the operation: success/fail? this will be specially important when we add the web interface to this.
    fun insert(expense: Expense) = viewModelScope.launch {
        repository.insert(expense)
    }
}

class ExpenseViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}