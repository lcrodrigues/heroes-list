package com.example.heroeslist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.repository.HeroesRepository

class ListViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private val _itemList = MutableLiveData<MutableList<String>>()
    val itemList: LiveData<MutableList<String>>
        get() = _itemList


    fun getList(address: String) {

    }
}
