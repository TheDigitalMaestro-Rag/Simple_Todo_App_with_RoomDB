package com.project.kotlinroomdb.DB

import android.app.Application
import androidx.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen

class mainApplication : Application() {

    companion object{
        lateinit var todoDataBase: TodoDataBase
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        todoDataBase = Room.databaseBuilder(
            applicationContext,
            TodoDataBase::class.java,
            TodoDataBase.NAME
        ).build()
    }
}