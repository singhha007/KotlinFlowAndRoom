package com.servicetitan.android.platform.kotlinflowroom.data.local

import androidx.room.TypeConverter

private const val COMMA = ","

class Converters {

    @TypeConverter
    fun stringToIntList(value: String): List<Int> = value.split(COMMA).map { it.toInt() }


    @TypeConverter
    fun intListToString(values: List<Int>): String = values.joinToString(COMMA)


    @TypeConverter
    fun stringToList(value: String): List<String> = value.split(COMMA)


    @TypeConverter
    fun listToString(values: List<String>): String = values.joinToString(COMMA)
}