package com.example.heroeslist.data.repository

import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.network.SafeRequest

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
                ts = System.currentTimeMillis().toString())
        }
}