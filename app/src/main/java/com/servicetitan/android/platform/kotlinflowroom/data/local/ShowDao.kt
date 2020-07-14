package com.servicetitan.android.platform.kotlinflowroom.data.local

import androidx.room.*
import com.servicetitan.android.platform.kotlinflowroom.data.model.Genre
import com.servicetitan.android.platform.kotlinflowroom.data.model.Show

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<T>)

    @Delete
    suspend fun delete(item: T): Int
}

@Dao
interface ShowDao: BaseDao<Show> {

    @Query("SELECT * FROM Show")
    suspend fun allShows(): List<Show>

    @Query("SELECT * FROM Show WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: String): Show
}

@Dao
interface GenreDao: BaseDao<Genre> {

    @Query("SELECT * FROM Genre")
    suspend fun allGenre(): List<Genre>

    @Query("SELECT * FROM Genre WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: String): Genre

    @Query("SELECT * FROM Genre WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): Genre
}