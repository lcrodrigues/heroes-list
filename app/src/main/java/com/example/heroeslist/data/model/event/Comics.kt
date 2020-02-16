package com.example.heroeslist.data.model.event

data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: String
)