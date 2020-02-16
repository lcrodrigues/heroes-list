package com.example.heroeslist.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.model.hero.Hero
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.util.Coroutines
import kotlinx.coroutines.Job

class HeroDetailsViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _hero = MutableLiveData<Hero>()
    val hero: LiveData<Hero>
        get() = _hero

    fun getHeroDetails(id: String) {
        job = Coroutines.ioThenMain(
            { repository.getHeroDetails(id) },
            { wrapper ->
                _hero.value = wrapper?.data?.results?.first()
            }
        )
    }

}
