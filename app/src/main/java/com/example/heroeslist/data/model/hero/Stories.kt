package com.example.heroeslist.data.model.hero

data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<StoriesItems>,
    val returned: String
)