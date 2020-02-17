package com.example.heroeslist.data.repository

import com.example.heroeslist.data.types.MediaType
import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.network.SafeRequest
import java.util.*

class HeroesRepository(
    private val heroesApi: HeroesApi
) : SafeRequest() {

    suspend fun getHeroes(limit: Int, offset: Int) =
        apiRequest {
            heroesApi.getHeroes(
                ts = System.currentTimeMillis().toString(),
                limit = limit,
                offset = offset
            )
        }

    suspend fun getHeroDetails(id: String) =
        apiRequest {
            heroesApi.getHeroDetails(
                id = id,
                ts = System.currentTimeMillis().toString()
            )
        }

    suspend fun getHeroComics(mediaType: MediaType, id: String, limit: Int, offset: Int) =
        apiRequest {
            heroesApi.getHeroComics(
                id = id,
                address = mediaType.value.toLowerCase(Locale.getDefault()),
                ts = System.currentTimeMillis().toString(),
                limit = limit,
                offset = offset
            )
        }

    suspend fun getHeroStories(mediaType: MediaType, id: String, limit: Int, offset: Int) =
        apiRequest {
            heroesApi.getHeroStories(
                id = id,
                address = mediaType.value.toLowerCase(Locale.getDefault()),
                ts = System.currentTimeMillis().toString(),
                limit = limit,
                offset = offset
            )
        }

    suspend fun getHeroEvents(mediaType: MediaType, id: String, limit: Int, offset: Int) =
        apiRequest {
            heroesApi.getHeroEvents(
                id = id,
                address = mediaType.value.toLowerCase(Locale.getDefault()),
                ts = System.currentTimeMillis().toString(),
                limit = limit,
                offset = offset
            )
        }

    suspend fun getHeroSeries(mediaType: MediaType, id: String, limit: Int, offset: Int) =
        apiRequest {
            heroesApi.getHeroSeries(
                id = id,
                address = mediaType.value.toLowerCase(Locale.getDefault()),
                ts = System.currentTimeMillis().toString(),
                limit = limit,
                offset = offset
            )
        }
}