package com.example.todo.Models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.todo.Types.TodoItems
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class TodoModel : ViewModel() {

    private val _todoList: SnapshotStateList<TodoItems> = mutableStateListOf<TodoItems>(TodoItems(todoType = "work", todoName = "Complete Android!!", taskDate = LocalDate.now(), id = 0, description = "Have to earn the android badge."))
    val todoList: SnapshotStateList<TodoItems> = _todoList

    fun addTodo(newTodo: TodoItems) {
        _todoList.add(newTodo)
        Log.d("TAG", "setListData: ${_todoList.size} == ${todoList.size}")
    }
}