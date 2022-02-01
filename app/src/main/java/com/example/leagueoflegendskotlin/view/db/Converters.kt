package com.example.leagueoflegendskotlin.view.db

import android.util.Log
import androidx.room.TypeConverter

private const val SEPARATOR = ","

class Converters {

    @TypeConverter
    fun fromTagsListToString(tagsList : List<String>) : String {
        return tagsList.joinToString(SEPARATOR)
    }

    @TypeConverter
    fun fromTagsStringToList(tagsString : String) : List<String>{
        return tagsString.split(SEPARATOR).toList()
    }

}