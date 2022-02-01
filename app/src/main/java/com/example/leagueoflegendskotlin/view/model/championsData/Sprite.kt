package com.example.leagueoflegendskotlin.view.model.championsData

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sprite(
    @SerializedName("url")
    val url: String,
    @SerializedName("x")
    val x: Int,
    @SerializedName("y")
    val y: Int
) : Serializable