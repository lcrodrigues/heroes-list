package com.example.heroeslist.data.repository

import com.example.heroeslist.data.network.HeroesApi
import com.example.heroeslist.data.network.SafeRequest

class HeroesRepository(
    private val heroesApi: HeroesApi
) : SafeRequest() {

    suspend fun getHeroes() = apiRequest { heroesApi.getHeroes(ts = System.currentTimeMillis().toString()) }
}