package com.example.todolist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTodoTitle: TextView
        val cbIsChecked: CheckBox

        init {
            tvTodoTitle = itemView.findViewById(R.id.tvTodoTitle)
            cbIsChecked = itemView.findViewById(R.id.cbIsChecked)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvTodoTitle = holder.tvTodoTitle
        val cbIsChecked = holder.cbIsChecked
        val currentTodo = todos[position]

        holder.itemView.apply {
            tvTodoTitle.text = currentTodo.title
            cbIsChecked.isChecked = currentTodo.isChecked
            checkTodoIfDone(tvTodoTitle, currentTodo.isChecked)
            cbIsChecked.setOnCheckedChangeListener { _, isChecked ->
                checkTodoIfDone(tvTodoTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }

    }

    override fun getItemCount(): Int = todos.size

    private fun checkTodoIfDone(tvTodoTitle: TextView, isCheck: Boolean) {
        if (isCheck) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun deleteAllDoneTodo() {
        todos.removeAll {
            it.isChecked
        }
        notifyDataSetChanged()
    }

}