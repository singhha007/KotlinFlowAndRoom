package com.servicetitan.android.platform.kotlinflowroom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.servicetitan.android.platform.kotlinflowroom.data.model.Genre
import com.servicetitan.android.platform.kotlinflowroom.data.model.Show

@Database(entities = [Show::class, Genre::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao
    abstract fun genreDao(): GenreDao
}