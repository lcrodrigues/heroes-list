package com.example.heroeslist.data.model.event

data class Characters(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)