package com.example.heroeslist.ui.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.heroeslist.data.repository.HeroesRepository

class HeroesViewModelFactory(
    private val repository: HeroesRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroesViewModel(repository) as T
    }
}