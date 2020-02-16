package com.example.heroeslist.data.model.story

data class Characters(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)