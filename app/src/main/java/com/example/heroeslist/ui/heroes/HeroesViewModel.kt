package com.example.heroeslist.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.model.hero.Hero
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.util.Coroutines
import kotlinx.coroutines.*

class HeroesViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _heroes = MutableLiveData<MutableList<Hero>>()
    val heroes: LiveData<MutableList<Hero>>
        get() = _heroes

    private val limit = 50
    private var offset = 0
    var totalItems: Int? = 0
    var isWaitingForRequest = false

    fun getHeroesList() {
        isWaitingForRequest = true

        job = Coroutines.ioThenMain(
            { repository.getHeroes(limit, offset) },
            { wrapper ->
                val list = _heroes.value ?: mutableListOf()
                val listFromApi = wrapper?.data?.results

                if(!listFromApi.isNullOrEmpty()) {
                    list.addAll(listFromApi)
                }

                if(!list.isNullOrEmpty()) {
                    _heroes.value = list
                }

                if(totalItems == 0) {
                    totalItems = wrapper?.data?.total?.toIntOrNull()
                }

                offset = (_heroes.value?.count() ?: 0)
            }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}
