package com.servicetitan.android.platform.kotlinflowroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Genre(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = -1,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null
)