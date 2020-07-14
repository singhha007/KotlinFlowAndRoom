package com.servicetitan.android.platform.kotlinflowroom.data.local

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME = "show-database"

object ShowDatabaseProvider {

    fun provideShowDatabase(context: Context) =
        Room.databaseBuilder(context, ShowDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
}