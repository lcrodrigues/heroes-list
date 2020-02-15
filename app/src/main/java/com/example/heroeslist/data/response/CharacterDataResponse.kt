package com.example.heroeslist.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataResponse(
    @Json(name = "results")
    val characterResponse: List<CharacterResponse>
)