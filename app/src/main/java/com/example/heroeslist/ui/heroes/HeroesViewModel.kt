package com.example.heroeslist.ui.heroes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.util.Coroutines
import kotlinx.coroutines.*

class HeroesViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>>
        get() = _heroes

    fun getHeroesList() {

        job = Coroutines.ioThenMain(
            { repository.getHeroes() },
            { wrapper ->
                val list = wrapper?.data?.results

                _heroes.value = list
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
