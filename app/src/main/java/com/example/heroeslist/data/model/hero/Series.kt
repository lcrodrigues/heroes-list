package com.example.heroeslist.data.model.hero

data class Series(
    val available: String,
    val collectionURI: String,
    val seriesItems: List<SeriesItem>,
    val returned: String
)