package com.example.heroeslist.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.data.repository.HeroesRepository
import kotlinx.coroutines.*

class HeroesViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>>
        get() = _heroes

    fun getHeroesList() {
        job = CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt repository.getHeroes()
            }.await()

            _heroes.value = data
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}
