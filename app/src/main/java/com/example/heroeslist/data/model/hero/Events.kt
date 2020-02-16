package com.example.heroeslist.data.model.hero

data class Events(
    val available: String,
    val collectionURI: String,
    val eventsItems: List<EventsItem>,
    val returned: String
)