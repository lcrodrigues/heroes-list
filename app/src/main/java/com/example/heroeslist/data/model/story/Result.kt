package com.example.heroeslist.data.model.story

data class Result(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: String,
    val events: Events,
    val id: String,
    val modified: String,
    val originalissue: Originalissue,
    val resourceURI: String,
    val series: Series,
    val thumbnail: Thumbnail,
    val title: String,
    val type: String
)