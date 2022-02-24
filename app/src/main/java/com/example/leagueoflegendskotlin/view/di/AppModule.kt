package com.example.leagueoflegendskotlin.view.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.leagueoflegendskotlin.view.db.ChampionDataBase
import com.example.leagueoflegendskotlin.view.util.CHAMPION_DATABASE_NAME
import com.example.leagueoflegendskotlin.view.viewmodel.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChampionDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ChampionDataBase::class.java,
        CHAMPION_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideChampionDao(dp : ChampionDataBase) = dp.getChampionDao()




}