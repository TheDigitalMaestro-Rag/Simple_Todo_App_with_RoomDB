package com.project.kotlinroomdb.DB

// FileName: MultipleFiles/Converters.kt
import androidx.room.TypeConverter
import org.threeten.bp.Instant // <--- Import org.threeten.bp.Instant

class Converters {
    // Convert Instant to Long (epoch milliseconds) for Room storage
    @TypeConverter
    fun fromInstant(instant: Instant?): Long? { // Use nullable types for flexibility
        return instant?.toEpochMilli()
    }

    // Convert Long (epoch milliseconds) back to Instant
    @TypeConverter
    fun toInstant(value: Long?): Instant? { // Use nullable types for flexibility
        return value?.let { Instant.ofEpochMilli(it) }
    }
}
