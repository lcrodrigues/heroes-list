package com.example.heroeslist.data.model

data class Series(
    val available: String,
    val collectionURI: String,
    val seriesItems: List<SeriesItem>,
    val returned: String
)