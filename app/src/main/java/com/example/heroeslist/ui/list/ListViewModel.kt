package com.example.heroeslist.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.MediaType
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.util.Coroutines
import kotlinx.coroutines.Job

class ListViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    lateinit var job: Job
    private val _itemList = MutableLiveData<MutableList<String>>()
    val itemList: LiveData<MutableList<String>>
        get() = _itemList


    fun getList(mediaType: MediaType, id: String) {
        job = Coroutines.ioThenMain(
            { repository.getHeroAppearances(mediaType, id) },
            { wrapper ->
                val list = wrapper?.data?.results
                val stringList = mutableListOf<String>()

                list?.forEach {
                    stringList.add(it.title)
                }

                _itemList.value = stringList
            }
        )
    }
}
