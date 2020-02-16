package com.example.heroeslist.data.model

data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<StoriesItems>,
    val returned: String
)