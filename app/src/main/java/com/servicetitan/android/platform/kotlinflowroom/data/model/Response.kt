package com.servicetitan.android.platform.kotlinflowroom.data.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class GenreResponse(
    @SerializedName("genres") val genres: List<Genre>
)