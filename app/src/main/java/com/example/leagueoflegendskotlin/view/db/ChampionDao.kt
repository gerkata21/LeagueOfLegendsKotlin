package com.example.leagueoflegendskotlin.view.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChampionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChampion(champion: Champion)

    @Delete
    suspend fun deleteChampion(champion: Champion)

    @Query("SELECT * FROM champion_table ORDER BY name ASC")
    fun getAllChampionsSortedByName() : LiveData<List<Champion>>
}