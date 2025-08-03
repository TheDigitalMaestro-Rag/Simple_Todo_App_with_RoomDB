package com.project.kotlinroomdb.DB

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.Instant
import java.util.Date

@Entity
data class TodoEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var createdAt: Instant
)