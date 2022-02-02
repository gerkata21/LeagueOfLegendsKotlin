package com.example.leagueoflegendskotlin.view.model.network

import com.example.leagueoflegendskotlin.view.model.championsData.Champions
import retrofit2.Response
import retrofit2.http.GET


interface LeagueAPIService {
    @GET("ngryman/lol-champions/master/champions.json")
    suspend fun getResponse():
            Response<Champions>
}