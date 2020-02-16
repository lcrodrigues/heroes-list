package com.example.heroeslist.data.network


import com.example.heroeslist.data.model.ResultWrapper
import com.example.heroeslist.util.PUBLIC_KEY
import com.example.heroeslist.util.BASE_URL
import com.example.heroeslist.util.getMD5
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesApi {

    @GET("characters")
    suspend fun getHeroes(
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts)
    ): Response<ResultWrapper>

    companion object {
        operator fun invoke(): HeroesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(HeroesApi::class.java)
        }
    }
}