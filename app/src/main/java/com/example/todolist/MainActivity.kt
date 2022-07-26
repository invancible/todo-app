package com.example.todolist

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var etAddTodo: EditText
    private lateinit var btnAddTodo: Button
    private lateinit var rvTodoItems: RecyclerView
    private lateinit var adapter: TodoAdapter
    private lateinit var todos: MutableList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set variable to its corresponding views
        etAddTodo = findViewById(R.id.etAddTodo)
        btnAddTodo = findViewById(R.id.btnAddTodo)
        rvTodoItems = findViewById(R.id.rvTodoItems)

        // Database-like where all data are stored
        todos = mutableListOf(
             Todo("Buy bread"),
             Todo("Read book"),
             Todo("Exercise"),
             Todo("Do homework"),
             Todo("Check email"),
        )

        adapter = TodoAdapter(todos)
        rvTodoItems.adapter = adapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todo = etAddTodo.text.toString()
            if (todo.isNotEmpty()) {
            todos.add(Todo(todo))
            etAddTodo.text.clear()
            adapter.notifyItemInserted(todos.size - 1)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miDeleteDone -> adapter.deleteAllDoneTodo()
        }
        return true
    }
}