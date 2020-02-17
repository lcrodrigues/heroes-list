package com.example.heroeslist.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heroeslist.data.types.MediaType
import com.example.heroeslist.data.repository.HeroesRepository
import com.example.heroeslist.util.Coroutines
import kotlinx.coroutines.Job

class ListViewModel(
    private val repository: HeroesRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _itemList = MutableLiveData<MutableList<String>>()
    val itemList: LiveData<MutableList<String>>
        get() = _itemList

    var totalITems: Int? = 0
    var isWaitingForRequest = false
    private val limit = 50
    private var offset = 0

    fun getList(mediaType: MediaType, id: String) {
        isWaitingForRequest = true

        job = when (mediaType) {
            MediaType.Comics -> {
                Coroutines.ioThenMain(
                    { repository.getHeroComics(mediaType, id, limit, offset) },
                    { comicsWrapper ->
                        val list = _itemList.value ?: mutableListOf()
                        val listFromApi = comicsWrapper?.data?.results

                        val stringList = mutableListOf<String>()

                        listFromApi?.forEach {
                            stringList.add(it.title)
                        }

                        list.addAll(stringList)
                        _itemList.value = list

                        if (totalITems == 0) {
                            totalITems = comicsWrapper?.data?.total?.toIntOrNull()
                        }
                    }
                )
            }

            MediaType.Stories -> {
                Coroutines.ioThenMain(
                    { repository.getHeroStories(mediaType, id, limit, offset) },
                    { storiesWrapper ->
                        val list = _itemList.value ?: mutableListOf()
                        val listFromApi = storiesWrapper?.data?.results

                        val stringList = mutableListOf<String>()

                        listFromApi?.forEach {
                            stringList.add(it.title)
                        }

                        list.addAll(stringList)
                        _itemList.value = list

                        if (totalITems == 0) {
                            totalITems = storiesWrapper?.data?.total?.toIntOrNull()
                        }
                    }
                )
            }

            MediaType.Series -> {
                Coroutines.ioThenMain(
                    { repository.getHeroSeries(mediaType, id, limit, offset) },
                    { seriesWrapper ->
                        val list = _itemList.value ?: mutableListOf()
                        val listFromApi = seriesWrapper?.data?.results

                        val stringList = mutableListOf<String>()

                        listFromApi?.forEach {
                            stringList.add(it.title)
                        }

                        list.addAll(stringList)
                        _itemList.value = list

                        if (totalITems == 0) {
                            totalITems = seriesWrapper?.data?.total?.toIntOrNull()
                        }
                    }
                )
            }

            MediaType.Events -> {
                Coroutines.ioThenMain(
                    { repository.getHeroEvents(mediaType, id, limit, offset) },
                    { eventsWrapper ->
                        val list = _itemList.value ?: mutableListOf()
                        val listFromApi = eventsWrapper?.data?.results

                        val stringList = mutableListOf<String>()

                        listFromApi?.forEach {
                            stringList.add(it.title)
                        }

                        list.addAll(stringList)
                        _itemList.value = list

                        if (totalITems == 0) {
                            totalITems = eventsWrapper?.data?.total?.toIntOrNull()
                        }
                    }
                )
            }
        }

        offset = (_itemList.value?.count() ?: 0)
    }
}
