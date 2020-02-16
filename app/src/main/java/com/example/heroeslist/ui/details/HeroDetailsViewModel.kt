package com.example.heroeslist.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.data.repository.HeroesRepository

class HeroDetailsViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private val _hero = MutableLiveData<Hero>()
    private val hero: LiveData<Hero>
        get() = _hero



}
