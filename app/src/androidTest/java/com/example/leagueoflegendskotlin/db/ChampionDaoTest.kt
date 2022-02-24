package com.example.leagueoflegendskotlin.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.leagueoflegendskotlin.getOrAwaitValue
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.db.ChampionDao
import com.example.leagueoflegendskotlin.view.db.ChampionDataBase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class ChampionDaoTest {

    private lateinit var database: ChampionDataBase
    private lateinit var dao: ChampionDao

    @Before
    fun setup(){

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ChampionDataBase::class.java
        ).allowMainThreadQueries().build()

        dao = database.getChampionDao()

    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertChampionTest() = runBlockingTest {
        val champion = Champion(
            "Test",
            "Test",
            "aatrox",
            null,
            null,
            "Test",
            false
        )
        dao.insertChampion(champion)

        val allChampions = dao.getAllChampionsSortedByName().getOrAwaitValue()

        assertThat(allChampions).contains(champion)
    }

    @Test
    fun deleteChampionTest(){

    }

}