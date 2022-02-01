package com.example.retrofitmvvmexample.network

import com.example.leagueoflegendskotlin.view.model.network.LeagueAPIService
import com.example.leagueoflegendskotlin.view.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private var RETROFIT_INSTANCE: Retrofit? = null

    fun getRetroServiceInterface(retrofit: Retrofit) : LeagueAPIService {
        return retrofit.create(LeagueAPIService::class.java)
    }

    fun getRetrofitClient(): Retrofit {
        //If null, create instance
        return RETROFIT_INSTANCE ?: synchronized(this) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val currentInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            RETROFIT_INSTANCE = currentInstance
            currentInstance
        }
    }
}