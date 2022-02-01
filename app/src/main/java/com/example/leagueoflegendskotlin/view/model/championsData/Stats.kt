package com.example.leagueoflegendskotlin.view.model.championsData

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Stats(
    @SerializedName("armor")
    val armor: Float,
    @SerializedName("armorperlevel")
    val armorPerLevel: Float,
    @SerializedName("attackdamage")
    val attackDamage: Float,
    @SerializedName("attackdamageperlevel")
    val attackDamagePerLevel: Float,
    @SerializedName("attackrange")
    val attackRange: Float,
    @SerializedName("attackspeed")
    val attackSpeed: Float,
    @SerializedName("attackspeedperlevel")
    val attackSpeedPerLevel: Float,
    @SerializedName("crit")
    val crit: Float,
    @SerializedName("critperlevel")
    val critPerLevel: Float,
    @SerializedName("hp")
    val hp: Float,
    @SerializedName("hpperlevel")
    val hpperLevel: Float,
    @SerializedName("hpregen")
    val hpRegen: Float,
    @SerializedName("hpregenperlevel")
    val hpRegenPerLevel: Float,
    @SerializedName("movespeed")
    val moveSpeed: Float,
    @SerializedName("mp")
    val mp: Float,
    @SerializedName("mpperlevel")
    val mpPerLevel: Float,
    @SerializedName("mpregen")
    val mpRegen: Float,
    @SerializedName("mpregenperlevel")
    val mpRegenPerLevel: Float,
    @SerializedName("spellblock")
    val spellBlock: Float,
    @SerializedName("spellblockperlevel")
    val spellBlockPerLevel: Float
): Serializable