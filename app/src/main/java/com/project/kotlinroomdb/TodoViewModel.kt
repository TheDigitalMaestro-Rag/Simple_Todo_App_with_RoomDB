// FileName: MultipleFiles/TodoViewModel.kt
package com.project.kotlinroomdb

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.kotlinroomdb.DB.TodoDao
import com.project.kotlinroomdb.DB.TodoEntity
import com.project.kotlinroomdb.DB.mainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.Instant // <--- Ensure this import is correct

class TodoViewModel : ViewModel() {

    val todoDao = mainApplication.todoDataBase.getTodoDao()

    val todoList : LiveData<List<TodoEntity>> = todoDao.getAllTodo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title : String){
        viewModelScope.launch(Dispatchers.IO) {
            val currentDate = Instant.now() // <--- Create org.threeten.bp.Instant directly
            todoDao.addTodo(TodoEntity(title = title, createdAt = currentDate)) // <--- Pass Instant directly
        }
    }

    fun deleteTodo(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.DeleteTodo(id)
        }
    }
}
