package com.example.heroeslist.data.model.hero

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<Hero>,
    val total: String
)