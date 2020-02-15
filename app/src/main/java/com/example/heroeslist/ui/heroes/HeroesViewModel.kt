package com.example.heroeslist.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.model.Hero

class HeroesViewModel : ViewModel() {

    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>>
        get() = _heroes

    fun getHeroesList(): List<Hero> {
        return listOf(
            Hero("Spider-Man"),
            Hero("Iron Man"),
            Hero("Hulk"),
            Hero("Thor")
        )
    }

}
