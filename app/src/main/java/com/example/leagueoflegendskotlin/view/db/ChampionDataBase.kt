package com.example.leagueoflegendskotlin.view.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Champion::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ChampionDataBase : RoomDatabase() {

    abstract fun getChampionDao() : ChampionDao

}