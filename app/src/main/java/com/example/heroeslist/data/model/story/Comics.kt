package com.example.heroeslist.data.model.story

data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: String
)