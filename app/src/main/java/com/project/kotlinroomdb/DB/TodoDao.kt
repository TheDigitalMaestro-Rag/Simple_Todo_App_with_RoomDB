package com.project.kotlinroomdb.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoEntity ORDER BY createdAt DESC ")
    fun getAllTodo() : LiveData<List<TodoEntity>>

    @Insert
    fun addTodo(todoEntity: TodoEntity)

    @Query("Delete From TodoEntity where id = :id")
    fun DeleteTodo(id: Int)
}