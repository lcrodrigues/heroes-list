package com.example.heroeslist.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterWrapperResponse(
    @Json(name = "data")
    val characterDataResponse: CharacterDataResponse
)
