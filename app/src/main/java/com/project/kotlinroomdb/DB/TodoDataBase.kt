package com.project.kotlinroomdb.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [TodoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDataBase : RoomDatabase(){

    companion object{
        const val NAME = "todo_DB"
    }

    abstract fun getTodoDao() : TodoDao
}