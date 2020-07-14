package com.servicetitan.android.platform.kotlinflowroom.data.remote

import com.servicetitan.android.platform.kotlinflowroom.data.model.GenreResponse
import com.servicetitan.android.platform.kotlinflowroom.data.model.Response
import com.servicetitan.android.platform.kotlinflowroom.data.model.Show
import retrofit2.http.GET

interface ShowApi {

    @GET("tv/popular")
    suspend fun popularShow(): Response<Show>

    @GET("genre/movie/list")
    suspend fun genre(): GenreResponse
}