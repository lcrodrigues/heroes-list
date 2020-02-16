package com.example.heroeslist.data.repository

import android.util.Log
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.data.network.HeroesApi
import retrofit2.Callback
import com.example.heroeslist.data.response.CharacterWrapperResponse
import retrofit2.Call
import retrofit2.Response

class HeroesRepository(
    val heroesApi: HeroesApi
) {

    suspend fun getHeroes(): List<Hero> {
        val heroesList = mutableListOf<Hero>()

        heroesApi.getHeroes(ts = "${System.currentTimeMillis()}")
            .enqueue(object : Callback<CharacterWrapperResponse> {
                override fun onResponse(
                    call: Call<CharacterWrapperResponse>,
                    response: Response<CharacterWrapperResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { characterWrapperResponse ->
                            val characterList =
                                characterWrapperResponse.characterDataResponse.characterResponse

                            characterList.forEach { character ->
                                heroesList.add(Hero(character.name))
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CharacterWrapperResponse>, t: Throwable) {

                }
            })

        Log.d("15022020", "size ${heroesList.count()}")
        return heroesList
    }
}