package com.example.heroeslist.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.heroeslist.data.repository.HeroesRepository

class HeroDetailsViewModelFactory(
    private val repository: HeroesRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeroDetailsViewModel(repository) as T
    }
}