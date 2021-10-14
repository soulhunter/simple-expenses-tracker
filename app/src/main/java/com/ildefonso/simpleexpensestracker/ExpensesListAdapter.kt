package com.ildefonso.simpleexpensestracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.ildefonso.simpleexpensestracker.ExpensesListAdapter.ExpenseViewHolder

class ExpensesListAdapter : ListAdapter<Expense, ExpenseViewHolder>(ExpensesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.date.toString().plus(" ").plus(current.amount.toString()))
    }

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val expenseItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            expenseItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ExpenseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ExpenseViewHolder(view)
            }
        }
    }

    class ExpensesComparator : DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
    }
}