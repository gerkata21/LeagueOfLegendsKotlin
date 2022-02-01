package com.example.leagueoflegendskotlin.view.model.championsData

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChampionsItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprite")
    val sprite: Sprite,
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("title")
    val title: String
) : Serializable