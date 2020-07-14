package com.servicetitan.android.platform.kotlinflowroom.data

import com.servicetitan.android.platform.kotlinflowroom.data.local.ShowDatabase
import com.servicetitan.android.platform.kotlinflowroom.data.model.Genre
import com.servicetitan.android.platform.kotlinflowroom.data.model.Show
import com.servicetitan.android.platform.kotlinflowroom.data.remote.ShowApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowApiRepository(private val showApi: ShowApi, private val showDatabase: ShowDatabase) {

    fun popularShow(): Flow<List<Show>> =
        flow {
            if(showDatabase.showDao().allShows().isEmpty()) {
                val shows: List<Show> = showApi.popularShow().results
                showDatabase.showDao().insert(shows)
            }
            emit(showDatabase.showDao().allShows())
        }

    fun genre(): Flow<List<Genre>> =
        flow {
            if(showDatabase.genreDao().allGenre().isEmpty()) {
                val genres = showApi.genre().genres
                showDatabase.genreDao().insert(genres)
            }
            emit(showDatabase.genreDao().allGenre())
        }
}