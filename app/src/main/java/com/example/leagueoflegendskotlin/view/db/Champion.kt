package com.example.leagueoflegendskotlin.view.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.leagueoflegendskotlin.view.model.championsData.Stats
import java.io.Serializable

@Entity(tableName = "champion_table", indices = [Index(value = ["name","title"], unique = true)])
data class Champion(
    val description: String = "",
    var icon: String = "",
    val name: String = "",
    @Embedded val stats: Stats? = null,
    val tags: List<String>? = null,
    val title: String = "",
    var linkFix : Boolean = false
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}