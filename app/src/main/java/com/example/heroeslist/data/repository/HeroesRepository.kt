package com.example.heroeslist.data.repository

import com.example.heroeslist.data.model.Hero

class HeroesRepository {

    fun getHeroes() = listOf(
        Hero("Spider-Man"),
        Hero("Iron Man"),
        Hero("Hulk"),
        Hero("Rogue"),
        Hero("Thor")
    )
}