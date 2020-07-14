package com.servicetitan.android.platform.kotlinflowroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Show")
data class Show(
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("first_air_date")
    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int> = emptyList(),

    @PrimaryKey @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = "",

    @SerializedName("origin_country")
    @ColumnInfo(name = "origin_country")
    var originCountry: List<String> = emptyList(),

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    var originalName: String? = null,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Int = 0,

    @Ignore var genreList: List<Genre> = emptyList()
) {
    fun updateGenre(genres: List<Genre>) {
        genreList = genres.filter { genreIds.contains(it.id) }
    }
}