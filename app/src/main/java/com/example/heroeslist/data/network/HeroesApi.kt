package com.example.heroeslist.data.network

import com.example.heroeslist.data.model.comic.ComicWrapper
import com.example.heroeslist.data.model.event.EventWrapper
import com.example.heroeslist.data.model.hero.ResultWrapper
import com.example.heroeslist.data.model.serie.SerieWrapper
import com.example.heroeslist.data.model.story.StoryWrapper
import com.example.heroeslist.util.getMD5
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesApi {

    @GET("characters")
    suspend fun getHeroes(
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts),
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ResultWrapper>

    @GET("characters/{characterId}")
    suspend fun getHeroDetails(
        @Path("characterId") id: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts)
    ): Response<ResultWrapper>

    @GET("characters/{characterId}/{address}")
    suspend fun getHeroComics(
        @Path("characterId") id: String,
        @Path("address") address: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts),
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ComicWrapper>

    @GET("characters/{characterId}/{address}")
    suspend fun getHeroEvents(
        @Path("characterId") id: String,
        @Path("address") address: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts),
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<EventWrapper>

    @GET("characters/{characterId}/{address}")
    suspend fun getHeroStories(
        @Path("characterId") id: String,
        @Path("address") address: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts),
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<StoryWrapper>

    @GET("characters/{characterId}/{address}")
    suspend fun getHeroSeries(
        @Path("characterId") id: String,
        @Path("address") address: String,
        @Query("apikey") apiKey: String = PUBLIC_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String = getMD5(ts),
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<SerieWrapper>

    companion object {
        operator fun invoke(): HeroesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(HeroesApi::class.java)
        }

        const val PUBLIC_KEY = "8f4214a98e94658ffa0449436e102b43"
        const val PRIVATE_KEY = "bbbee0bfb47fc353f88793f275dc437921608ed1"
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }
}