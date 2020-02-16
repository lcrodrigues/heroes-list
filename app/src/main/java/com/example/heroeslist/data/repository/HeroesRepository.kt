package com.example.heroeslist.data.repository

import android.util.Log
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.data.model.ResultWrapper
import com.example.heroeslist.data.network.HeroesApi
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class HeroesRepository(
    val heroesApi: HeroesApi
) {

    suspend fun getHeroes(): List<Hero> {
        val heroesList = mutableListOf<Hero>()

        heroesApi.getHeroes(ts = "${System.currentTimeMillis()}")
            .enqueue(object : Callback<ResultWrapper> {
                override fun onResponse(
                    call: Call<ResultWrapper>,
                    response: Response<ResultWrapper>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultWrapper ->

                        }
                    }
                }

                override fun onFailure(call: Call<ResultWrapper>, t: Throwable) {

                }
            })

        Log.d("15022020", "size ${heroesList.count()}")
        return heroesList
    }
}